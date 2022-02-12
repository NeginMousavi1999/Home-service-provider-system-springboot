package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.dto.mapper.CustomerMapper;
import ir.maktab.project.data.dto.mapper.UserMapper;
import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.data.repository.CustomerRepository;
import ir.maktab.project.exceptions.DuplicateException;
import ir.maktab.project.exceptions.NotFoundException;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.OrderService;
import ir.maktab.project.validation.Validation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@Service
@RequiredArgsConstructor
@Getter
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final Validation validation;
    private final ModelMapper modelMapper = new ModelMapper();
    private final OrderService orderService;
    private final Environment environment;

    public void update(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto);
        customerRepository.save(customer);
    }

    public void save(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new DuplicateException(environment.getProperty("Duplicate.Username"));
        }
    }

    public CustomerDto findByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if (customer.isEmpty())
            throw new NotFoundException(environment.getProperty("No.Customer.Found"));
        return CustomerMapper.mapCustomerToCustomerDto(customer.get());
    }

    public Long getCountOfRecords() {
        return customerRepository.count();
    }

    @Override
    public void increaseCredit(CustomerDto customerDto, double amount) {
        double oldCredit = customerDto.getCredit();
        customerDto.setCredit(oldCredit + amount);
        update(customerDto);
    }

    @Override
    public void changePassword(CustomerDto customerDto, String oldPassword, String newPassword) {
        validation.validateUserRole(UserRole.CUSTOMER, customerDto.getUserRole());
        validation.validateCorrectPassword(oldPassword, customerDto.getPassword());
        validation.validatePassword(newPassword);
        customerDto.setPassword(newPassword);
        update(customerDto);
    }

    @Override
    public CustomerDto payByCredit(CustomerDto customerDto, double price) {
        validation.validateCustomerCredit(customerDto.getCredit(), price);
        customerDto.setCredit(customerDto.getCredit() - price);
        update(customerDto);
        return customerDto;
    }

    @Override
    public Map<UserDto, Integer> getCustomerAndNumberOfRegisteredRequests() {
        List<CustomerDto> customers = getAll();
        Map<UserDto, Integer> map = new HashMap<>();
        for (CustomerDto customer : customers) {
            int count = orderService.findNumberOfRegisteredRequestsByCustomer(customer);
            map.put(UserMapper.mapCustomerToUserDto(customer), count);
        }
        return map;
    }

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        return customers.stream().map(CustomerMapper::mapCustomerToCustomerDto).collect(Collectors.toList());
    }

    @Override
    public void confirmEmail(CustomerDto customerDto) {
        customerDto.setUserStatus(UserStatus.CONFIRMED);
        update(customerDto);
    }

    @Override
    public CustomerDto findByIdentity(int identity) {
        Optional<Customer> optionalCustomer = customerRepository.findById(identity - 1000);
        if (optionalCustomer.isEmpty())
            throw new NotFoundException(environment.getProperty("No.Customer.Found"));
        return CustomerMapper.mapCustomerToCustomerDto(optionalCustomer.get());
    }
}

package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.entity.members.Customer;

/**
 * @author Negin Mousavi
 */
public class CustomerMapper {
    private static final int suffix = 1000;

    public static Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getIdentity() - suffix)
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .password(customerDto.getPassword())
                .credit(customerDto.getCredit())
                .userStatus(customerDto.getUserStatus())
                .userRole(customerDto.getUserRole())
                .registrationDate(customerDto.getRegistrationDate())
                .build();
    }

    public static CustomerDto mapCustomerToCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .identity(customer.getId() + suffix)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .credit(customer.getCredit())
                .userStatus(customer.getUserStatus())
                .userRole(customer.getUserRole())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }
}

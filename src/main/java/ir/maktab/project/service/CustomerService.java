package ir.maktab.project.service;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * @author Negin Mousavi
 */
public interface CustomerService {
    void update(CustomerDto customer);

    void save(CustomerDto customer);

    CustomerDto findByEmail(String email);

    Long getCountOfRecords();

    void increseCredit(CustomerDto customerDto, double amount);

    void changePassword(CustomerDto customerDto, String oldPassword, String newPassword);

    CustomerDto payByCredit(CustomerDto customerDto, double price);

    Map<UserDto, Integer> getCustomerAndNumberOfRegisteredRequests();

    List<CustomerDto> getAll();
}

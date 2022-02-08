package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.entity.members.User;

/**
 * @author Negin Mousavi
 */
public class UserMapper {
    public static UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .identity(user.getId() + 1000)
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .credit(user.getCredit())
                .email(user.getEmail())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .userStatus(user.getUserStatus())
                .registrationDate(user.getRegistrationDate())
                .build();
    }

    public static User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getIdentity() - 1000)
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .credit(userDto.getCredit())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .userRole(userDto.getUserRole())
                .userStatus(userDto.getUserStatus())
                .registrationDate(userDto.getRegistrationDate())
                .build();
    }

    public static UserDto mapCustomerToUserDto(CustomerDto customer) {
        return UserDto.builder()
                .identity(customer.getIdentity())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .credit(customer.getCredit())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .userRole(customer.getUserRole())
                .userStatus(customer.getUserStatus())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }

    public static UserDto mapExpertToUserDto(ExpertDto expert) {
        return UserDto.builder()
                .identity(expert.getIdentity())
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .credit(expert.getCredit())
                .email(expert.getEmail())
                .password(expert.getPassword())
                .userRole(expert.getUserRole())
                .userStatus(expert.getUserStatus())
                .registrationDate(expert.getRegistrationDate())
                .build();
    }
}

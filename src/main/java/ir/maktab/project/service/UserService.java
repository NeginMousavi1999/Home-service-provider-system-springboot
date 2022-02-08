package ir.maktab.project.service;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.data.entity.members.User;
import ir.maktab.project.data.enumuration.UserStatus;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface UserService {
    List<UserDto> returnUsersFiltering(UserRequestDto request);

    UserDto createUserDto(User user);

    UserDto findUserByUserNameAndPassword(LoginDto loginDto);

    List<UserDto> returnWaitingUsers();

    void updateUserStatus(int identity, UserStatus userStatus);

    void createVerificationToken(UserDto userDto, String token);

    VerificationTokenDto getVerificationToken(String VerificationToken);

    UserDto getCustomerDtoByVerificationToken(String verificationToken);

    void usedToken(VerificationTokenDto verificationToken);
}

package ir.maktab.project.service;

import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.dto.UserRequestDto;
import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.entity.members.User;
import ir.maktab.project.data.enumuration.UserStatus;

import java.util.List;

/**
 * @author Negin Mousavi
 */

public interface UserService {
    List<UserDto> returnUsersFiltering(UserRequestDto request);

    UserDto createUserDto(User user);

    void updateUserStatus(int identity, UserStatus userStatus);

    void createVerificationToken(UserDto userDto, String token);

    VerificationTokenDto getVerificationToken(String VerificationToken);

    void usedToken(VerificationTokenDto verificationToken);

    UserDto findByIdentity(int identity);
}

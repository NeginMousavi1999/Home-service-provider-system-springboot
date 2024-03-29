package ir.maktab.project.service;

import ir.maktab.project.data.dto.LoginDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.dto.UserRequestDto;
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
}

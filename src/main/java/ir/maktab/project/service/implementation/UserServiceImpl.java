package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.LoginDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.dto.UserRequestDto;
import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.entity.members.User;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.data.repository.UserRepository;
import ir.maktab.project.exception.HomeServiceException;
import ir.maktab.project.service.UserService;
import ir.maktab.project.service.VerificationTokenService;
import ir.maktab.project.util.mapper.UserMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final VerificationTokenService verificationTokenService;

    public UserDto findUserByUserNameAndPassword(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (user.isEmpty())
            throw new HomeServiceException("username or password is incorrect");
        return createUserDto(user.get());
    }

    public List<UserDto> returnUsersFiltering(UserRequestDto request) {
        return userRepository.findAll(UserRepository.selectByConditions(request))
                .stream().map(this::createUserDto).collect(Collectors.toList());
    }

    public UserDto createUserDto(User user) {
        return UserMapper.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> returnWaitingUsers() {
        Optional<List<User>> optionalUsers = userRepository.findByUserStatus(UserStatus.WAITING);
        if (optionalUsers.isEmpty())
            throw new HomeServiceException("no waiting user!");
        return optionalUsers.get().stream().map(this::createUserDto).collect(Collectors.toList());
    }

    @Override
    public void updateUserStatus(int identity, UserStatus userStatus) {
        userRepository.updateStatus(identity - 1000, userStatus);
    }

    @Override
    public void createVerificationToken(UserDto userDto, String token) {
        VerificationTokenDto myToken = new VerificationTokenDto(token, userDto);
        verificationTokenService.save(myToken);
    }

    @Override
    public VerificationTokenDto getVerificationToken(String VerificationToken) {
        return verificationTokenService.findByToken(VerificationToken);
    }

    @Override
    public UserDto getCustomerDtoByVerificationToken(String verificationToken) {
        return verificationTokenService.findByToken(verificationToken).getUserDto();
    }

    public void usedToken(VerificationTokenDto verificationToken) {
        verificationTokenService.hasUsedToken(verificationToken);
    }

    @Override
    public UserDto findByIdentity(int identity) {
        Optional<User> optionalUser = userRepository.findById(identity - 1000);
        if (optionalUser.isEmpty())
            throw new HomeServiceException("user not found");
        return UserMapper.mapUserToUserDto(optionalUser.get());
    }
}

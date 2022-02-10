package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.entity.members.VerificationToken;

/**
 * @author Negin Mousavi
 */
public class VerificationTokenMapper {
    private static final int suffix = 1000;

    public static VerificationToken mapVerificationTokenDtoToVerificationToken(VerificationTokenDto tokenDto) {
        return VerificationToken.builder()
                .id(tokenDto.getIdentity() - suffix)
                .usedCount(tokenDto.getUsedCount())
                .expireDate(tokenDto.getExpireDate())
                .user(UserMapper.mapUserDtoToUser(tokenDto.getUserDto()))
                .token(tokenDto.getToken())
                .build();
    }

    public static VerificationToken mapVerificationTokenDtoToVerificationTokenForSaving(VerificationTokenDto tokenDto) {
        return VerificationToken.builder()
                .user(UserMapper.mapUserDtoToUser(tokenDto.getUserDto()))
                .expireDate(tokenDto.getExpireDate())
                .token(tokenDto.getToken())
                .usedCount(0)
                .build();
    }

    public static VerificationTokenDto mapVerificationTokenToVerificationTokenDto(VerificationToken token) {
        return VerificationTokenDto.builder()
                .identity(token.getId() + suffix)
                .userDto(UserMapper.mapUserToUserDto(token.getUser()))
                .expireDate(token.getExpireDate())
                .token(token.getToken())
                .usedCount(token.getUsedCount())
                .build();
    }
}

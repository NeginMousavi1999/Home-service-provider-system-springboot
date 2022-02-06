package ir.maktab.project.util.mapper;

import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.entity.members.VerificationToken;

/**
 * @author Negin Mousavi
 */
public class VerificationTokenMapper {
    private static final int suffix = 1000;

    public static VerificationToken mapVerificationTokenDtoToVerificationToken(VerificationTokenDto tokenDto) {
        return VerificationToken.builder()

                .build();
    }

    public static VerificationToken mapVerificationTokenDtoToVerificationTokenForSaving(VerificationTokenDto tokenDto) {
        return VerificationToken.builder()
                .customer(CustomerMapper.mapCustomerDtoToCustomer(tokenDto.getCustomerDto()))
                .expireDate(tokenDto.getExpireDate())
                .token(tokenDto.getToken())
                .build();
    }

    public static VerificationTokenDto mapVerificationTokenToVerificationTokenDto(VerificationToken token) {
        return VerificationTokenDto.builder()
                .identity(token.getId() + suffix)
                .customerDto(CustomerMapper.mapCustomerToCustomerDto(token.getCustomer()))
                .expireDate(token.getExpireDate())
                .token(token.getToken())
                .build();
    }
}

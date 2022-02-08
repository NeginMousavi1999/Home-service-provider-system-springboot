package ir.maktab.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenDto {
    private static final int EXPIRATION = 60 * 24;

    private Long identity;

    private String token;

    private UserDto userDto;

    private Date expireDate;

    private int usedCount;

    public VerificationTokenDto(String token, UserDto userDto) {
        this.token = token;
        this.userDto = userDto;
    }
}

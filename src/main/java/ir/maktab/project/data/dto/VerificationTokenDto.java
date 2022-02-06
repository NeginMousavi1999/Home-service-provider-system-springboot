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

    private CustomerDto customerDto;

    private Date expireDate;

    public VerificationTokenDto(String token, CustomerDto customerDto) {
        this.token = token;
        this.customerDto = customerDto;
    }
}

package ir.maktab.project.data.dto;

import ir.maktab.project.data.enumuration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Long identity;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole userRole;
    private String serviceName;
}

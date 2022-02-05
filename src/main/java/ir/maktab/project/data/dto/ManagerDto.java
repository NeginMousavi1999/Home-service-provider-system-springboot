package ir.maktab.project.data.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class ManagerDto {
    private Long identity;
    private String email;
    private String password;
}

package ir.maktab.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDto {
    private int identity;
    private String name;
    private Set<SubServiceDto> subServices;
}

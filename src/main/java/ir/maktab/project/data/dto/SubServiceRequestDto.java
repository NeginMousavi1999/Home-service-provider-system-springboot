package ir.maktab.project.data.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author Negin Mousavi
 */
@Data
public class SubServiceRequestDto {
    private String name;
    private String serviceName;
    private double cost;
    @Size(max = 300, message = "it is too long!")
    private String description;
}

package ir.maktab.project.data.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author Negin Mousavi
 */
@Data
public class OrderRequestDto {
    private String subServiceName;
    @Size(max = 300, message = "it is too long!")
    private String description;
    private String country;
    private String city;
    private String state;
    @Size(min = 5, max = 10, message = "must be between 5 and 10 digit")
    private String postalCode;
}

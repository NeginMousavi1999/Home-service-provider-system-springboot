package ir.maktab.project.data.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class NewOrderDto {
    private String subServiceName;
    private String description;
    private String city;
    private String state;
    private String neighbourhood;
    private String formatted_address;
}

package ir.maktab.project.data.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class AddressDto {
    private int identity;
    private String city;
    private String state;
    private String neighbourhood;
    private String formattedAddress;
}

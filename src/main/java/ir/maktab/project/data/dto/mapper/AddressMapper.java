package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.AddressDto;
import ir.maktab.project.data.entity.order.Address;

/**
 * @author Negin Mousavi
 */
public class AddressMapper {
    private static final int suffix = 1000;

    public static Address mapAddressDtoToAddress(AddressDto addressDto) {
        return Address.builder()
                .id(addressDto.getIdentity() - suffix)
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .neighbourhood(addressDto.getNeighbourhood())
                .formattedAddress(addressDto.getFormattedAddress())
                .build();
    }

    public static Address mapAddressDtoToAddressForSaving(AddressDto addressDto) {
        return Address.builder()
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .neighbourhood(addressDto.getNeighbourhood())
                .formattedAddress(addressDto.getFormattedAddress())
                .build();
    }

    public static AddressDto mapAddressToAddressDto(Address address) {
        return AddressDto.builder()
                .identity(address.getId() + suffix)
                .city(address.getCity())
                .state(address.getState())
                .neighbourhood(address.getNeighbourhood())
                .formattedAddress(address.getFormattedAddress())
                .build();
    }
}

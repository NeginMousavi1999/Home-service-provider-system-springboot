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
                .country(addressDto.getCountry())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .postalCode(addressDto.getPostalCode())
                .build();
    }

    public static Address mapAddressDtoToAddressForSaving(AddressDto addressDto) {
        return Address.builder()
                .country(addressDto.getCountry())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .postalCode(addressDto.getPostalCode())
                .build();
    }

    public static AddressDto mapAddressToAddressDto(Address address) {
        return AddressDto.builder()
                .identity(address.getId() + suffix)
                .country(address.getCountry())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .build();
    }
}

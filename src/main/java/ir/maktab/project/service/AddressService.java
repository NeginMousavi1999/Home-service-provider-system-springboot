package ir.maktab.project.service;

import ir.maktab.project.data.dto.AddressDto;

/**
 * @author Negin Mousavi
 */
public interface AddressService {
    void save(AddressDto addressDto);

    AddressDto findAddress(String country, String city, String state, String postalCode);
}

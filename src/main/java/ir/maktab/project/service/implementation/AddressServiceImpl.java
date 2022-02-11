package ir.maktab.project.service.implementation;

import ir.maktab.project.data.dto.AddressDto;
import ir.maktab.project.data.dto.mapper.AddressMapper;
import ir.maktab.project.data.entity.order.Address;
import ir.maktab.project.data.repository.AddressRepository;
import ir.maktab.project.service.AddressService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@RequiredArgsConstructor
@Service
@Getter
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Override
    public void save(AddressDto addressDto) {
        addressRepository.save(AddressMapper.mapAddressDtoToAddressForSaving(addressDto));
    }

    @Override
    public AddressDto findAddress(String city, String state, String neighbourhood, String formattedAddress) {
        Optional<Address> optionalAddress = addressRepository
                .findByCityAndStateAndNeighbourhoodAndFormattedAddress(city, state, neighbourhood, formattedAddress);
        if (optionalAddress.isEmpty())
            return null;
        return AddressMapper.mapAddressToAddressDto(optionalAddress.get());
    }
}

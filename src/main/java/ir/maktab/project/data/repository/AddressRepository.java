package ir.maktab.project.data.repository;


import ir.maktab.project.data.entity.order.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
    Optional<Address> findByCountryAndCityAndStateAndPostalCode(String country, String city, String state, String postalCode);
}

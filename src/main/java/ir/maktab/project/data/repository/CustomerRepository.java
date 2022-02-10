package ir.maktab.project.data.repository;

import ir.maktab.project.data.entity.members.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findById(long id);
}

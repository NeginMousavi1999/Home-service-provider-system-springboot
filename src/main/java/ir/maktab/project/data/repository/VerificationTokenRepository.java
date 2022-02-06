package ir.maktab.project.data.repository;

import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.entity.members.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByCustomer(Customer customer);
}

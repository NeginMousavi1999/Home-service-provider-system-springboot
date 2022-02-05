package ir.maktab.project.data.repository;

import ir.maktab.project.data.entity.members.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
    Optional<Manager> findByEmailAndPassword(String email, String password);
}

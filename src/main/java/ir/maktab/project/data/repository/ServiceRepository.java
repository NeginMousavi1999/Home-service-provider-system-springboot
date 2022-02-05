package ir.maktab.project.data.repository;

import ir.maktab.project.data.entity.services.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {

    List<Service> findAll();

    Optional<Service> findByName(String name);

    @Query(value = "from Service s join fetch s.subServices")
    Optional<List<Service>> getAllIncludeSubService();
}

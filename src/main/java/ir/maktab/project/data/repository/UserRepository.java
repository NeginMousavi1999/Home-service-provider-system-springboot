package ir.maktab.project.data.repository;

import ir.maktab.project.data.dto.UserRequestDto;
import ir.maktab.project.data.entity.members.User;
import ir.maktab.project.data.enumuration.UserStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

    static Specification<User> selectByConditions(UserRequestDto request) {
        return (Specification<User>) (root, cq, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (request.getFirstName() != null && request.getFirstName().length() != 0)
                predicateList.add(cb.equal(root.get("firstName"), request.getFirstName()));
            if (request.getLastName() != null && request.getLastName().length() != 0)
                predicateList.add(cb.equal(root.get("lastName"), request.getLastName()));
            if (request.getEmail() != null && request.getEmail().length() != 0)
                predicateList.add(cb.equal(root.get("email"), request.getEmail()));
            if (request.getUserRole() != null)
                predicateList.add(cb.equal(root.get("userRole"), request.getUserRole()));
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<List<User>> findByUserStatus(UserStatus userStatus);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.userStatus=:userStatus where u.id=:id")
    void updateStatus(@Param("id") long id, @Param("userStatus") UserStatus userStatus);

    Optional<User> findByEmail(String email);
}

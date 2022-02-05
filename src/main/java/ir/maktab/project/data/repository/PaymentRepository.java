package ir.maktab.project.data.repository;


import ir.maktab.project.data.entity.order.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Negin Mousavi
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {

}

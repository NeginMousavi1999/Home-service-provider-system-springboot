package ir.maktab.project.data.repository;


import ir.maktab.project.data.entity.order.Order;
import ir.maktab.project.data.entity.order.OrderProcess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Negin Mousavi
 */
@Repository
public interface OrderProcessRepository extends CrudRepository<OrderProcess, Integer> {
    Optional<OrderProcess> findByOrder(Order order);
}

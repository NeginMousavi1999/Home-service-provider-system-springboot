package ir.maktab.project.data.repository;

import ir.maktab.project.data.dto.OrdersHistoryDto;
import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.entity.members.Expert;
import ir.maktab.project.data.entity.order.Address;
import ir.maktab.project.data.entity.order.Order;
import ir.maktab.project.data.entity.services.Service;
import ir.maktab.project.data.entity.services.SubService;
import ir.maktab.project.data.enumuration.OrderStatus;
import ir.maktab.project.util.GenerateDate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    static Specification<Order> selectByConditions(OrdersHistoryDto conditions) {
        return (Specification<Order>) (root, cq, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (conditions.getFromDate() != null && conditions.getFromDate().length() != 0)
                predicateList.add(cb.greaterThanOrEqualTo(root.get("registrationDate"),
                        GenerateDate.generateByPattern("yyyy-MM-dd", conditions.getFromDate())));

            if (conditions.getToDate() != null && conditions.getToDate().length() != 0)
                predicateList.add(cb.lessThanOrEqualTo(root.get("toBeDoneDate"),
                        GenerateDate.generateByPattern("yyyy-MM-dd", conditions.getToDate())));

            if (conditions.getStatus() != null && conditions.getStatus().length() != 0)
                predicateList.add(cb.equal(root.get("orderStatus"), OrderStatus.valueOf(conditions.getStatus().toUpperCase())));

            if (conditions.getService() != null && conditions.getService().length() != 0) {
                Join<Order, Service> service = root.join("subService").join("service");
                predicateList.add(cb.equal(service.get("name"), conditions.getService()));
            }

            if (conditions.getSubService() != null && conditions.getSubService().length() != 0) {
                Join<Order, SubService> subService = root.join("subService");
                predicateList.add(cb.equal(subService.get("name"), conditions.getSubService()));
            }

            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }

    Optional<List<Order>> findBySubService(SubService subService);

    Optional<List<Order>> findByCustomer(Customer customer);

    Optional<List<Order>> findByCustomerAndOrderStatus(Customer customer, OrderStatus orderStatus);

    @Transactional
    @Modifying
    @Query(value = "update System_Order o set o.orderStatus=:orderStatus where o.id=:id")
    void updateStatus(@Param("id") int id, @Param("orderStatus") OrderStatus orderStatus);

    @Query(value = "from System_Order o where o.orderStatus=:orderStatus and o.subService in :subServices")
    Optional<List<Order>> findReadyOrdersForExpert(@Param("orderStatus") OrderStatus orderStatus,
                                                   @Param("subServices") Set<SubService> subServices);

    Optional<List<Order>> findByExpertAndOrderStatus(Expert expert, OrderStatus orderStatus);

    @Query(value = "from System_Order o where o.orderStatus<>:orderStatus1 and o.orderStatus<>:orderStatus2")
    Optional<List<Order>> findByNotEqualsSatus(@Param("orderStatus1") OrderStatus orderStatus1,
                                               @Param("orderStatus2") OrderStatus orderStatus2);

    int countByCustomer(Customer customer);

    int countByExpert(Expert expert);

    Optional<Order> findByAddressAndCustomerAndSubServiceAndDescription(Address address, Customer customer,
                                                                        SubService subService, String description);
}

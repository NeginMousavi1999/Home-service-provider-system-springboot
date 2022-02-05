package ir.maktab.project.data.entity.order;

import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.entity.members.Expert;
import ir.maktab.project.data.entity.services.SubService;
import ir.maktab.project.data.enumuration.OrderStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Entity(name = "System_Order")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "sub_service_id")
    private SubService subService;

    @Column(length = 120)
    private String description;

    @Column(name = "final_price")
    private double finalPrice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @CreationTimestamp
    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "to_be_done_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date toBeDoneDate;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Expert expert;

    @OneToMany(mappedBy = "order")
    private Set<Suggestion> suggestions = new HashSet<>();
}

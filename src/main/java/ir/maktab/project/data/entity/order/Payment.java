package ir.maktab.project.data.entity.order;

import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.enumuration.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne
    private Order order;

    @ManyToOne
    private Customer customer;

    @CreationTimestamp
    private Date paymentDate;

    private String cardNumber;
}

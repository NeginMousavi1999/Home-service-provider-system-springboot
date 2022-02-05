package ir.maktab.project.data.entity.order;

import ir.maktab.project.data.entity.members.Customer;
import ir.maktab.project.data.entity.members.Expert;
import lombok.*;

import javax.persistence.*;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Expert expert;

    @OneToOne
    private Order order;

    private double score;
}

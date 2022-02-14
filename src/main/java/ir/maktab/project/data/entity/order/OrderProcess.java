package ir.maktab.project.data.entity.order;

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
public class OrderProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(name = "start_time")
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "finish_time")
    private Date finishTime;

    @OneToOne
    private Order order;
}

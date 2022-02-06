package ir.maktab.project.data.entity.members;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "customer_id")
    private Customer customer;

    private Date expireDate;

    @Column(name = "used_count")
    private int usedCount;
}
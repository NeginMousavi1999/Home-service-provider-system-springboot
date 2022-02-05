package ir.maktab.project.data.entity.members;

import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected long id;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(unique = true)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Column(name = "user_status")
    @Enumerated(value = EnumType.STRING)
    protected UserStatus userStatus;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    protected UserRole userRole;

    @Column(name = "registration_date")
    @CreationTimestamp
    protected Date registrationDate;

    private double credit;
}

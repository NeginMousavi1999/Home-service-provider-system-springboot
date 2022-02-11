package ir.maktab.project.data.entity.order;

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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String city;

    private String state;

    private String neighbourhood;

    @Column(name = "formatted_address")
    private String formattedAddress;
}

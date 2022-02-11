package ir.maktab.project.data.dto;

import ir.maktab.project.data.enumuration.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private int identity;

    private PaymentMethod paymentMethod;

    private OrderDto order;

    private CustomerDto customerDto;

    private Date paymentDate;

    @Size(max = 12, min = 8, message = "invalid card number!")
    private String cardNumber;

    private String cost;
}

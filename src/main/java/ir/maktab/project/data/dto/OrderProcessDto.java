package ir.maktab.project.data.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
public class OrderProcessDto {
    private int identity;
    private Date startTime;
    private Date finishTime;
    private OrderDto order;
}

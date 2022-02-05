package ir.maktab.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    private Long identity;
    private String comment;
    private CustomerDto customer;
    private ExpertDto expert;
    private OrderDto order;

    @Max(5)
    @Min(0)
    private String score;
}

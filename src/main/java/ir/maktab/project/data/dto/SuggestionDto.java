package ir.maktab.project.data.dto;

import ir.maktab.project.data.enumuration.SuggestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionDto {
    private int identity;
    private ExpertDto expert;
    private OrderDto order;
    private Date registrationDate;
    private double suggestedPrice;
    private int durationOfWork;
    private Date startTime;
    private SuggestionStatus suggestionStatus;
}

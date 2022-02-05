package ir.maktab.project.data.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Negin Mousavi
 */
@Data
public class SuggestionRequestDto {
    @Min(1)
    private double suggestedPrice;

    @Min(1)
    private int durationOfWork;

    @NotBlank
    private String startTime;
}

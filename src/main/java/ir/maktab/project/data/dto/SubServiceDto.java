package ir.maktab.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Negin Mousavi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubServiceDto {
    private int identity;
    private String name;
    private ServiceDto service;
    private double cost;
    private String description;
}

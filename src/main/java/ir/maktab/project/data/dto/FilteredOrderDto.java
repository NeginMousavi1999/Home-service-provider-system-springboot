package ir.maktab.project.data.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class FilteredOrderDto {

    private String fromDate;

    private String toDate;

    private String status;

    private String service;

    private String subService;

    private String customerUsername;
}

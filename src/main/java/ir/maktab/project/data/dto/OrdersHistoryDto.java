package ir.maktab.project.data.dto;

import lombok.Data;

/**
 * @author Negin Mousavi
 */
@Data
public class OrdersHistoryDto {

    //    @Pattern(regexp = "yyyy-MM-dd", message = "date format is like: yyyy-MM-dd")
    private String fromDate;

    //    @Pattern(regexp = "yyyy-MM-dd", message = "date format is like: yyyy-MM-dd")
    private String toDate;

    private String status;

    private String service;

    private String subService;

}

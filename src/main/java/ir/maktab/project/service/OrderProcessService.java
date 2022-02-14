package ir.maktab.project.service;

import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.OrderProcessDto;

import java.util.Date;

/**
 * @author Negin Mousavi
 */
public interface OrderProcessService {
    void save(OrderProcessDto orderProcessDto);

    int getActualDurationOfDoingOrderProcess(OrderProcessDto orderProcessDto);

    int getActualDurationOfWork(Date start, Date finish);

    OrderProcessDto findByOrder(OrderDto orderDto);
}

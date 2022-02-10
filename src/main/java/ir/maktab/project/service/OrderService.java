package ir.maktab.project.service;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.data.enumuration.OrderStatus;

import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */

public interface OrderService {

    void updateStatus(OrderDto order);

    void saveOrder(OrderDto order);

    OrderDto findById(int id);

    List<OrderDto> findBySubService(SubServiceDto subService);

    Set<OrderDto> getOrdersByCustomer(CustomerDto customer);

    List<OrderDto> getOrdersByCustomerAndStatus(CustomerDto customer, OrderStatus orderStatus);

    List<OrderDto> getOrdersReadyForSuggestion(ExpertDto expertDto);

    void update(OrderDto order);

    List<OrderDto> getOrdersToStartByExpert(ExpertDto expertDto);

    List<OrderDto> getOrdersToFinishByExpert(ExpertDto expertDto);

    void finishOrder(OrderDto orderDto);

    void startOrder(OrderDto orderDto);

    OrderDto addNewOrder(OrderRequestDto orderRequest, CustomerDto customerDto);

    List<OrderDto> getOrdersGivenByCustomer();

    List<OrderDto> getOrdersDoneByExpert();

    List<OrderDto> filteredOrders(CustomerDto customerDto, FilteredOrderDto conditions);

    int findNumberOfRegisteredRequestsByCustomer(CustomerDto customer);

    int findNumberOfOrdersPlacedByExpert(ExpertDto expert);

    List<OrderDto> getOrdersByExpert(ExpertDto expertDto);
}

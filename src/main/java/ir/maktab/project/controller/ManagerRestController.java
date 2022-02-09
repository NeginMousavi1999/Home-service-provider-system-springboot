package ir.maktab.project.controller;

import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.data.dto.OrdersHistoryDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class ManagerRestController {
    private final OrderService orderService;
    private final CustomerService customerService;
    private final ExpertService expertService;

    @GetMapping(value = "get_customers_services")
    @ResponseBody
    public List<OrderDto> getAllCustomersSystemService() {
        return orderService.getOrdersGivenByCustomer();
    }

    @GetMapping(value = "get_experts_services")
    @ResponseBody
    public List<OrderDto> getAllExpertsSystemService() {
        return orderService.getOrdersDoneByExpert();
    }

    @PostMapping(value = "get_by_conditions")
    @ResponseBody
    public List<OrderDto> getAllOrdersByConditions(@RequestBody OrdersHistoryDto conditions) {
        return orderService.filteredOrders(conditions);
    }

    @GetMapping("get_reports")
    @ResponseBody
    public Map<UserDto, Integer> getUsersReports() {
        HashMap<UserDto, Integer> map = new HashMap<>();
        Map<UserDto, Integer> customers = customerService.getCustomerAndNumberOfRegisteredRequests();
        Map<UserDto, Integer> experts = expertService.getExpertAndNumberOfRegisteredRequests();
        map.putAll(customers);
        map.putAll(experts);
        return map;
    }
}

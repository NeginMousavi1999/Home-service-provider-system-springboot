package ir.maktab.project.controller.manager;

import io.swagger.annotations.ApiOperation;
import ir.maktab.project.data.dto.*;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public List<OrderDto> getAllOrdersByConditions(@RequestBody FilteredOrderDto conditions, HttpServletRequest request) {
        HttpSession session = request.getSession();
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customer_showing_orders");
        return orderService.filteredOrders(customerDto, conditions);
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

    @GetMapping("get_customer_services/{identity}")
    @ResponseBody
    public List<OrderDto> getServicesByCustomer(@PathVariable("identity") int identity) {
        CustomerDto customerDto = customerService.findByIdentity(identity);
        return new ArrayList<>(orderService.getOrdersByCustomer(customerDto));
    }

    @ApiOperation(value = "get services used by expert")
    @GetMapping("get_expert_services/{identity}")
    @ResponseBody
    public List<OrderDto> getServicesByExpert(@PathVariable("identity") int identity) {
        ExpertDto expertDto = expertService.findById(identity);
        return orderService.getOrdersByExpert(expertDto);
    }
}

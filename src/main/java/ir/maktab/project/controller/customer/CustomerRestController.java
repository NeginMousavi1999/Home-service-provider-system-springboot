package ir.maktab.project.controller.customer;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.NewOrderDto;
import ir.maktab.project.data.dto.OrderDto;
import ir.maktab.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Negin Mousavi
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerRestController {
    private final OrderService orderService;

    @PostMapping("/doAddOrder")
    public String doAddOrder(@RequestBody NewOrderDto orderRequestDto, Model model,
                             HttpServletRequest request) {
        try {
            CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
            OrderDto orderDto = orderService.addNewOrderNew(orderRequestDto, customerDto);
            model.addAttribute("order", orderDto)
                    .addAttribute("succ_massage", "successfully added");
            return "/customer/customer_dashboard";
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "/customer/customer_dashboard";
        }
    }
}
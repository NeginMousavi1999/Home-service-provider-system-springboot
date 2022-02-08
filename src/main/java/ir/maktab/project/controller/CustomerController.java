package ir.maktab.project.controller;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.data.enumuration.PaymentMethod;
import ir.maktab.project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final ServiceService serviceService;
    private final OrderService orderService;
    private final SuggestionService suggestionService;
    private final FeedbackService feedbackService;
    private final PaymentService paymentService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/customer/customer_dashboard";
    }

    @GetMapping("/change_password")
    public String accessToChangePassword() {
        return "customer/change_password";
    }

    @PostMapping("/update_password")
    public String changePassword(@RequestParam(name = "userName") String email,
                                 @RequestParam(name = "oldPass") String oldPassword,
                                 @RequestParam(name = "newPass") String newPassword, Model model) {
        try {
            CustomerDto customerDto = customerService.findByEmail(email);
            customerService.changePassword(customerDto, oldPassword, newPassword);
            model.addAttribute("customer", customerDto);
            model.addAttribute("succ_massage", "successfully changed");
            return "customer/change_password";
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "customer/change_password";
        }
    }

    @GetMapping("/add_order")
    public ModelAndView showAddNewOrder(ModelAndView modelAndView) {
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        modelAndView.setViewName("customer/add_order");
        modelAndView.getModelMap().addAttribute("set", services);
        modelAndView.getModelMap().addAttribute("order", new OrderRequestDto());
        return modelAndView;
    }

    @PostMapping(value = "/add_new_order")
    public ModelAndView addNewOrder(@Validated @ModelAttribute("order") OrderRequestDto orderRequest, ModelAndView modelAndView,
                                    HttpServletRequest request) {
        try {
            CustomerDto customerDto = (CustomerDto) request.getSession().getAttribute("customerDto");
            OrderDto orderDto = orderService.addNewOrder(orderRequest, customerDto);
            modelAndView.getModelMap().addAttribute("order", orderDto);
            modelAndView.getModelMap().addAttribute("succ_massage", "successfully added");
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        } catch (Exception e) {
            modelAndView.getModelMap().addAttribute("error_massage", e.getLocalizedMessage());
            modelAndView.setViewName("customer/add_order");
            return showAddNewOrder(modelAndView);
        }
    }

    @GetMapping("/paying_from_credit/{identity}")
    public String payFromCredit(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDto paymentOrder = getPaymentOrderDto(identity, session);
        PaymentDto paymentDto = PaymentDto.builder()
                .order(paymentOrder)
                .paymentMethod(PaymentMethod.CREDIT)
                .build();
        try {
            CustomerDto customerDto = paymentService.pay(paymentDto);
            session.setAttribute("customerDto", customerDto);
            model.addAttribute("succ_massage", "successfully paid");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showAllOrders(request, model);
    }

    @GetMapping("/paying_online/{identity}")
    public String showOnlinePayPage(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDto paymentOrder = getPaymentOrderDto(identity, session);
        PaymentDto paymentDto = PaymentDto.builder()
                .order(paymentOrder)
                .paymentMethod(PaymentMethod.ONLINE)
                .build();
        model.addAttribute("paymentDto", paymentDto);
        session.setAttribute("paymentDto", paymentDto);
        return "customer/pay_online";
    }

    private OrderDto getPaymentOrderDto(int identity, HttpSession session) {
        Set<OrderDto> doneOrders = (Set<OrderDto>) session.getAttribute("customer_orders");
        return doneOrders.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
    }

    @PostMapping("/pay_online")
    public String doOnlinePay(@Validated @ModelAttribute("paymentDto") PaymentDto donePaymentDto, Model model,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            PaymentDto paymentDto = (PaymentDto) session.getAttribute("paymentDto");
            paymentDto.setCardNumber(donePaymentDto.getCardNumber());
            CustomerDto customerDto = paymentService.pay(paymentDto);
            session.setAttribute("customerDto", customerDto);
            model.addAttribute("succ_massage", "successfully paid");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showAllOrders(request, model);
    }

    @GetMapping("/show_feedback_page/{identity}")
    public String showFeedbackPage(@PathVariable("identity") int orderIdentity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Set<OrderDto> doneOrders = (Set<OrderDto>) session.getAttribute("customer_orders");
        OrderDto chosenOrder = doneOrders.stream().filter(order -> order.getIdentity() == orderIdentity).findFirst().orElse(null);
        CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
        assert chosenOrder != null;
        FeedbackDto feedbackDto = FeedbackDto.builder()
                .order(chosenOrder)
                .customer(customerDto)
                .expert(chosenOrder.getExpert())
                .build();
        model.addAttribute("feedbackDto", feedbackDto);
        session.setAttribute("feedbackDto", feedbackDto);
        return "customer/feedback_page";
    }

    @PostMapping("/show_feedback_page/feedback")
    public String feedback(@Validated @ModelAttribute("feedbackDto") FeedbackDto feedbackDtoJsp, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            FeedbackDto feedbackDto = (FeedbackDto) session.getAttribute("feedbackDto");
            feedbackService.addFeedback(feedbackDto, feedbackDtoJsp.getScore(), feedbackDtoJsp.getComment());
            model.addAttribute("succ_massage", "feedback added successfully");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return showAllOrders(request, model);
    }

    @GetMapping("/bank")
    public String showIncreasePage() {
        return "customer/increase_credit";
    }

    @PostMapping("/increase_credit")
    public String increaseCredit(@RequestParam(value = "amount") String stringAmount, HttpServletRequest request,
                                 Model model) {
        HttpSession session = request.getSession();
        try {
            CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
            double amount = Double.parseDouble(stringAmount);
            customerService.increaseCredit(customerDto, amount);
            model.addAttribute("succ_massage", "successfully increased");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "customer/increase_credit";
    }

    @GetMapping("/all_orders")
    public String showAllOrders(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
            Set<OrderDto> orders = orderService.getOrdersByCustomer(customerDto);
            session.setAttribute("customer_orders", orders);
            model.addAttribute("orders", orders);
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "customer/orders";
    }

    @GetMapping("/show_order_suggestions_sortedByExpertAndPrice/{identity}")
    public String showOrderSuggestions(@PathVariable("identity") int identity, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Set<OrderDto> orders = (Set<OrderDto>) session.getAttribute("customer_orders");
        OrderDto orderDto = orders.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
        List<SuggestionDto> suggestions = suggestionService.getSortedBySuggestedPriceAndExpertByOrder(orderDto);
        model.addAttribute("suggestions", suggestions);
        session.setAttribute("customer_suggestions", suggestions);
        return "customer/suggestions";
    }

    @GetMapping("/show_order_suggestions_sortedByExpert/{identity}")
    public String showOrderSuggestionsWithExpertScore(@PathVariable("identity") int identity, HttpServletRequest request,
                                                      Model model) {
        HttpSession session = request.getSession();
        Set<OrderDto> orders = (Set<OrderDto>) session.getAttribute("customer_orders");
        OrderDto orderDto = orders.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
        List<SuggestionDto> suggestions = suggestionService.getSortedByExpertByOrder(orderDto);
        model.addAttribute("suggestions", suggestions);
        session.setAttribute("customer_suggestions", suggestions);
        return "customer/suggestions";
    }

    @GetMapping("/show_order_suggestions_sortedByPrice/{identity}")
    public String showOrderSuggestionsWithSuggestedPrice(@PathVariable("identity") int identity, HttpServletRequest request,
                                                         Model model) {
        HttpSession session = request.getSession();
        Set<OrderDto> orders = (Set<OrderDto>) session.getAttribute("customer_orders");
        OrderDto orderDto = orders.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
        List<SuggestionDto> suggestions = suggestionService.getSortedBySuggestedPriceByOrder(orderDto);
        model.addAttribute("suggestions", suggestions);
        session.setAttribute("customer_suggestions", suggestions);
        return "customer/suggestions";
    }

    @GetMapping("/accept_suggestion/{identity}")
    public String acceptSuggestion(@PathVariable("identity") int identity, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("customer_suggestions");
        suggestionService.chooseSuggestion(identity, new ArrayList<>(suggestions));
        model.addAttribute("succ_massage", "successfully added");
        SuggestionDto suggestionDto = suggestions.stream().filter(order -> order.getIdentity() == identity).findFirst().orElse(null);
        assert suggestionDto != null;
        return showOrderSuggestions(suggestionDto.getOrder().getIdentity(), request, model);
    }
}

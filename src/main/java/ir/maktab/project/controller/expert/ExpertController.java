package ir.maktab.project.controller.expert;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/expert")
@RequiredArgsConstructor
public class ExpertController {
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;
    private final ExpertService expertService;
    private final OrderService orderService;
    private final SuggestionService suggestionService;
    private final FeedbackService feedbackService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "expert/expert_dashboard";
    }

    @GetMapping("/add_subservice")
    public String showAddSubService(HttpServletRequest request, Model model) {
        ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        Set<SubServiceDto> expertSubservices = expertService.getSubServices(expertDto);
        model.addAttribute("services", services)
                .addAttribute("expert_subservices", expertSubservices);
        return "expert/add_subservices";
    }

    @PostMapping("/add_to_services")
    public String addService(@RequestParam(name = "subservice_name") String subServiceName,
                             HttpServletRequest request, Model model) {
        try {
            ExpertDto expertDto = (ExpertDto) request.getSession().getAttribute("expertDto");
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(subServiceName);
            expertService.addSubServices(expertDto, subServiceDto);
            model.addAttribute("succ_massage", "successfully added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return showAddSubService(request, model);
    }

    @GetMapping("/show_orders")
    public String showOrdersForSuggesting(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        Set<SubServiceDto> subServices = expertService.getSubServices(expertDto);
        expertDto.setSubServiceDtos(subServices);
        List<OrderDto> ordersReadyForSuggestion = orderService.getOrdersReadyForSuggestion(expertDto);
        model.addAttribute("orders", ordersReadyForSuggestion);
        session.setAttribute("ordersReadyForSuggestion", ordersReadyForSuggestion);
        return "expert/show_orders";
    }

    @GetMapping("/show_order_suggestions/{identity}")
    public String showOrderSuggestions(@PathVariable("identity") int identity, HttpServletRequest request,
                                       Model model) {
        HttpSession session = request.getSession();
        List<OrderDto> ordersReadyForSuggestion = (List<OrderDto>) session.getAttribute("ordersReadyForSuggestion");
        OrderDto orderDto = ordersReadyForSuggestion.stream().filter(dto -> dto.getIdentity() == identity)
                .findFirst().orElse(null);
        Set<SuggestionDto> suggestionDtoList = suggestionService.getByOrder(orderDto);
        model.addAttribute("order_suggestions", suggestionDtoList);
        return "expert/show_order_suggestions";
    }

    @GetMapping("/add_suggestion/{identity}")
    public String showAddNewSuggestion(HttpServletRequest request, @PathVariable("identity") int identity, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("orderSuggestedIdentity", identity);
        model.addAttribute("suggestion_request", new SuggestionRequestDto());
        return "expert/add_suggestion";
    }

    @PostMapping("/add_new_suggestion")
    public String addSuggestion(@ModelAttribute SuggestionRequestDto suggestionRequest, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        OrderDto orderDto = null;
        try {
            ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
            int identity = (int) session.getAttribute("orderSuggestedIdentity");
            List<OrderDto> ordersReadyForSuggestion = (List<OrderDto>) session.getAttribute("ordersReadyForSuggestion");
            orderDto = ordersReadyForSuggestion.stream().filter(dto -> dto.getIdentity() == identity)
                    .findFirst().orElse(null);
            expertService.addNewSuggestion(suggestionRequest.getStartTime(),
                    suggestionRequest.getSuggestedPrice(), suggestionRequest.getDurationOfWork(), orderDto, expertDto);
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            assert orderDto != null;
            showAddNewSuggestion(request, orderDto.getIdentity(), model);
            return "expert/add_suggestion";
        }
        return showExpertTasks(request, model);
    }

    @GetMapping("/show_tasks")
    public String showExpertTasks(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        ExpertDto expertDto = (ExpertDto) session.getAttribute("expertDto");
        List<SuggestionDto> suggestionsDto = expertService.getSuggestions(expertDto);
        session.setAttribute("expert_suggestions", suggestionsDto);
        model.addAttribute("expert_suggestions", suggestionsDto);
        return "expert/tasks";
    }

    @GetMapping("/start/{identity}")
    public String startOrder(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
            SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                    .orElse(null);
            assert suggestionDto != null;
            expertService.startOrder(suggestionDto.getOrder());
            model.addAttribute("succ_massage", "order started successfully");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "expert/tasks";
    }

    @GetMapping("/finish/{identity}")
    public String finishOrder(@PathVariable("identity") int identity, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        try {
            List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
            SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                    .orElse(null);
            assert suggestionDto != null;
            ExpertDto expertDto = expertService.finishOrder(suggestionDto.getOrder(), suggestionDto.getDurationOfWork());
            session.setAttribute("expertDto", expertDto);
            model.addAttribute("succ_massage", "order finished successfully");
        } catch (Exception exception) {
            model.addAttribute("error_massage", exception.getLocalizedMessage());
        }
        return "expert/tasks";
    }

    @GetMapping("/show_feedback/{identity}")
    public String showCustomerFeedback(@PathVariable("identity") int identity, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        List<SuggestionDto> suggestions = (List<SuggestionDto>) session.getAttribute("expert_suggestions");
        SuggestionDto suggestionDto = suggestions.stream().filter(dto -> dto.getIdentity() == identity).findFirst()
                .orElse(null);
        assert suggestionDto != null;
        FeedbackDto feedbackDto = feedbackService.getByExpertAndOrder(suggestionDto.getExpert(), suggestionDto.getOrder());
        model.addAttribute("feedback", feedbackDto);
        return "expert/show_feedback";
    }
}

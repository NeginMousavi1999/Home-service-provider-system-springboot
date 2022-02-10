package ir.maktab.project.controller;

import ir.maktab.project.data.dto.*;
import ir.maktab.project.service.ManagerService;
import ir.maktab.project.service.ServiceService;
import ir.maktab.project.service.SubServiceService;
import ir.maktab.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Negin Mousavi
 */
@Controller
@RequestMapping("/portal/admin")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;
    private final UserService userService;
    private final ServiceService serviceService;
    private final SubServiceService subServiceService;

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "/manager/manager_dashboard";
    }

    @GetMapping("/dashboard/search")
    public String search(Model model) {
        model.addAttribute("filterData", new UserRequestDto());
        return "manager/search";
    }

    @PostMapping("/dashboard/doFilter")
    public String doFilter(@ModelAttribute("filterData") UserRequestDto userRequestDto, Model model) {
        List<UserDto> userDtoList = userService.returnUsersFiltering(userRequestDto);
        model.addAttribute("users", userDtoList);
        return "manager/search";
    }

    @GetMapping("/dashboard/confirm/{identity}")
    public String confirm(@PathVariable("identity") int identity, Model model) {
        try {
            managerService.confirmUser(identity);
            model.addAttribute("succ_massage", "confirmed successfully");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return "redirect:/portal/admin/dashboard/search";
    }

    @GetMapping("/dashboard/show_subservices_for_expert/{identity}")
    public String showSubServiceForExpert(@PathVariable("identity") int identity, HttpServletRequest request, Model model) {
        Set<ServiceDto> services = serviceService.getAllServiceIncludingSubService();
        Map<ExpertDto, Set<SubServiceDto>> expertSubservices = managerService.getExpertAndSubServices(identity);
        Collection<Set<SubServiceDto>> values = expertSubservices.values();
        for (Set<SubServiceDto> subServiceDtoSet : values) {
            model.addAttribute("services", services)
                    .addAttribute("expert_subservices", subServiceDtoSet);
        }
        request.getSession().setAttribute("expert_and_subservices", expertSubservices);
        return "manager/add_subservices";
    }

    @PostMapping("/dashboard/add_to_subservices")
    public String addService(@RequestParam(name = "subservice_name") String subServiceName,
                             HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        try {
            Map<ExpertDto, Set<SubServiceDto>> expertAndSubservices = (Map<ExpertDto, Set<SubServiceDto>>) session
                    .getAttribute("expert_and_subservices");
            Set<ExpertDto> expertDtoSet = expertAndSubservices.keySet();
            ExpertDto expertDto = null;
            for (ExpertDto myExpertDto : expertDtoSet)
                expertDto = myExpertDto;
            Set<SubServiceDto> subServiceDtoSet = expertAndSubservices.get(expertDto);
            assert expertDto != null;
            expertDto.setSubServiceDtos(subServiceDtoSet);
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(subServiceName);
            managerService.addSubServices(expertDto, subServiceDto);
            model.addAttribute("succ_massage", "successfully added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return "redirect:/portal/admin/dashboard/search";
    }

    @GetMapping("/dashboard/add_service")
    public String showAddNewService(Model model) {
        model.addAttribute("service", new ServiceDto());
        return "manager/add_service";
    }

    @PostMapping("/dashboard/add_new_service")
    public String addNewService(@ModelAttribute("service") ServiceDto serviceDto, Model model) {
        try {
            serviceService.addNewService(serviceDto);
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
            return "manager/add_service";
        }
        model.addAttribute("succ_massage", "successfully added");
        return "manager/add_service";
    }

    @GetMapping("/dashboard/add_subservice")
    public String showAddNewSubService(Model model) {
        List<String> serviceNameList = serviceService.getAllServiceName();
        model.addAttribute("subservice", new SubServiceRequestDto());
        model.addAttribute("list", serviceNameList);
        return "manager/add_subservice";
    }

    @PostMapping("/dashboard/add_new_subservice")
    public String addNewSubService(@Validated @ModelAttribute("subservice") SubServiceRequestDto subServiceRequestDto, Model model) {
        try {
            subServiceService.addNewSubService(subServiceRequestDto);
            model.addAttribute("succ_massage", "successfully added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return showAddNewSubService(model);
    }

    @GetMapping("/dashboard/show_orders")
    public String showOrders() {
        return "manager/show_orders";
    }

    @GetMapping("/dashboard/show_services_history")
    public String showServicesHistory() {
        return "manager/show_services_history";
    }

    @GetMapping("/dashboard/show_users_request")
    public String showUsersRequest() {
        return "manager/show_users_request";
    }

    @GetMapping("/dashboard/show_customer_services/{role}/{identity}")
    public String showCustomerServices(@PathVariable("identity") int identity, @PathVariable("role") String role,
                                       Model model) {
        UserDto userDto = userService.findByIdentity(identity);
        String fullName = userDto.getFirstName() + " " + userDto.getLastName();
        if (role.equals("CUSTOMER")) {
            model.addAttribute("customer_identity_showing_services", identity)
                    .addAttribute("name", fullName);
            return "manager/show_customer_services";
        } else {
            model.addAttribute("expert_identity_showing_services", identity)
                    .addAttribute("name", fullName);
            return "manager/show_expert_services";
        }
    }
}

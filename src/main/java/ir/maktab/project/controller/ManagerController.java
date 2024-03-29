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
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") @Validated LoginDto loginDto, Model model) {
        ManagerDto managerDto;
        try {
            managerDto = managerService.findByUserNameAndPassword(loginDto);
            model.addAttribute("managerDto", managerDto);
            return "manager/manager_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "login";
        }
    }

    @GetMapping("/dashboard/search")
    public String search(Model model) {
        model.addAttribute("filterData", new UserRequestDto());
        return "manager/search";
    }

    @PostMapping("/dashboard/doFilter")
    public String doFilter(@ModelAttribute("filterData") UserRequestDto userRequestDto, Model model) {
        List<UserDto> userDtos = userService.returnUsersFiltering(userRequestDto);
        model.addAttribute("users", userDtos);
        return "manager/search";
    }

    @GetMapping("/dashboard/confirm/{identity}")
    public String confirm(@PathVariable("identity") int identity, Model model) {
        try {
            managerService.confirmUser(identity);
            model.addAttribute("succ_massage", "confirmed successfuly");
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
        for (Set<SubServiceDto> subServiceDtos : values) {
            model.addAttribute("services", services)
                    .addAttribute("expert_subservices", subServiceDtos);
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
            Set<ExpertDto> expertDtos = expertAndSubservices.keySet();
            ExpertDto expertDto = null;
            for (ExpertDto myExpertDto : expertDtos)
                expertDto = myExpertDto;
            Set<SubServiceDto> subServiceDtos = expertAndSubservices.get(expertDto);
            assert expertDto != null;
            expertDto.setSubServiceDtos(subServiceDtos);
            SubServiceDto subServiceDto = subServiceService.findSubServiceByName(subServiceName);
            managerService.addSubServices(expertDto, subServiceDto);
            model.addAttribute("succ_massage", "successfuly added");
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
        model.addAttribute("succ_massage", "successfuly added");
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
            model.addAttribute("succ_massage", "successfuly added");
        } catch (Exception e) {
            model.addAttribute("error_massage", e.getLocalizedMessage());
        }
        return showAddNewSubService(model);
    }
}

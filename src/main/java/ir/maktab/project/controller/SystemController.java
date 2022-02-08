package ir.maktab.project.controller;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Negin Mousavi
 */
@Controller
@RequiredArgsConstructor
public class SystemController {
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final RegistrationListener registrationListener;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.getModelMap().addAttribute("registerData", new UserDto());
        return modelAndView;
    }

    @PostMapping("/doRegister")
    public String doRegister(@RequestParam("file") MultipartFile file,
                             @ModelAttribute("registerData") @Validated UserDto userDto,
                             Model model, HttpServletRequest request) {
        HttpSession session;
        userDto.setUserStatus(UserStatus.WAITING);

        if (userDto.getUserRole().equals(UserRole.EXPERT)) {
            byte[] pictureBytes;
            ExpertDto expertDto;
            try {
                pictureBytes = file.getBytes();
                userDto.setPicture(pictureBytes);
                expertDto = modelMapper.map(userDto, ExpertDto.class);
                expertService.save(expertDto);
            } catch (Exception e) {
                model.addAttribute("massage", e.getLocalizedMessage());
                return "expert/expert_dashboard";
            }
            session = request.getSession();
            session.setAttribute("expertDto", expertDto);
            return "expert/expert_dashboard";
        }

        CustomerDto customerDto;
        try {
            customerDto = modelMapper.map(userDto, CustomerDto.class);
            customerService.save(customerDto);
            customerDto = customerService.findByEmail(userDto.getEmail());
            String appUrl = request.getContextPath();
            OnRegistrationCompleteEvent event = new OnRegistrationCompleteEvent(customerDto, request.getLocale(), appUrl);
            registrationListener.onApplicationEvent(event);

        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "customer/customer_dashboard";
        }
        session = request.getSession();
        session.setAttribute("customerDto", customerDto);
        return "customer/customer_dashboard";
    }

    @GetMapping("/error")
    public String error(Model model){
        model.addAttribute("message"
                , "ERROR!");
        return "error_system";
    }
}

package ir.maktab.project.controller;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.LoginDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
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
    private final UserService userService;
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final RegistrationListener registrationListener;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.getModelMap().addAttribute("loginData", new LoginDto());
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginData") @Validated LoginDto loginDto, Model model, HttpServletRequest request) {
        UserDto user;
        HttpSession session;
        try {
            user = userService.findUserByUserNameAndPassword(loginDto);
            switch (user.getUserRole()) {
                case CUSTOMER:
                    CustomerDto customerDto = customerService.findByEmail(user.getEmail());
                    session = request.getSession();
                    session.setAttribute("customerDto", customerDto);
                    return "redirect:/customer/dashboard";
                case EXPERT:
                    ExpertDto expertDto = expertService.findByEmail(user.getEmail());
                    session = request.getSession();
                    session.setAttribute("expertDto", expertDto);
                    return "redirect:/expert/dashboard";
                default:
                    return "login";
            }
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "login";
        }
    }

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
                             Model model, HttpServletRequest request, Errors errors) {
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
//            eventPublisher.publishEvent(event);
            registrationListener.onApplicationEvent(event);

        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "customer/customer_dashboard";
        }
        session = request.getSession();
        session.setAttribute("customerDto", customerDto);
        return "customer/customer_dashboard";
    }
}

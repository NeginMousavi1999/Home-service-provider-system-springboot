package ir.maktab.project.controller;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.data.dto.ExpertDto;
import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.data.dto.VerificationTokenDto;
import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.service.CustomerService;
import ir.maktab.project.service.ExpertService;
import ir.maktab.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;

/**
 * @author Negin Mousavi
 */
@Controller
@RequiredArgsConstructor
public class SystemController {
    private final CustomerService customerService;
    private final ExpertService expertService;
    private final UserService userService;
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
        userDto.setUserStatus(UserStatus.NOT_CONFIRMED);

        String appUrl = request.getContextPath();
        OnRegistrationCompleteEvent event;

        if (userDto.getUserRole().equals(UserRole.EXPERT)) {
            byte[] pictureBytes;
            ExpertDto expertDto;
            try {
                pictureBytes = file.getBytes();
                userDto.setPicture(pictureBytes);
                expertDto = modelMapper.map(userDto, ExpertDto.class);
                expertService.save(expertDto);
                expertDto = expertService.findByEmail(userDto.getEmail());
                event = new OnRegistrationCompleteEvent(expertDto, request.getLocale(), appUrl);
                registrationListener.onApplicationEvent(event);
                session = request.getSession();
                session.setAttribute("expertDto", expertDto);
                return "expert/expert_dashboard";
            } catch (Exception e) {
                model.addAttribute("massage", e.getLocalizedMessage());
                return "expert/expert_dashboard";
            }
        }

        CustomerDto customerDto;
        try {
            customerDto = modelMapper.map(userDto, CustomerDto.class);
            customerService.save(customerDto);
            customerDto = customerService.findByEmail(userDto.getEmail());
            event = new OnRegistrationCompleteEvent(customerDto, request.getLocale(), appUrl);
            registrationListener.onApplicationEvent(event);
            session = request.getSession();
            session.setAttribute("customerDto", customerDto);
            return "customer/customer_dashboard";
        } catch (Exception e) {
            model.addAttribute("massage", e.getLocalizedMessage());
            return "customer/customer_dashboard";
        }
    }

    @GetMapping("/error")
    public String error(Model model) {
        model.addAttribute("message"
                , "ERROR!");
        return "error_system";
    }

    @GetMapping(value = "/sign_out")
    public String signOut(HttpServletRequest request) {
        HttpSession session;
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null)
            session.invalidate();
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "login";
    }

    @GetMapping("/user_confirmation")
    public String confirmRegistration
            (HttpServletRequest request, Model model, @RequestParam("token") String token) { //TODO needs to be login first

        VerificationTokenDto verificationToken;
        try {
            verificationToken = userService.getVerificationToken(token);
        } catch (Exception exception) {
            model.addAttribute("message", exception.getLocalizedMessage());
            return "error_system";
        }
        if (verificationToken == null) {
            model.addAttribute("message", "no such a verification token");
            return "error_system";
        }

        UserDto userDto = verificationToken.getUserDto();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpireDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", "verification token expired!");
            return "error_system";
        }

        if (verificationToken.getUsedCount() > 0) {
            model.addAttribute("message"
                    , "you are already confirmed and you can use registration link only once!");
            return "error_system";
        }
        userService.usedToken(verificationToken);
        HttpSession session = request.getSession();
        String username = userDto.getEmail();
        if (userDto.getUserRole().equals(UserRole.EXPERT)) {
            ExpertDto expertDto = expertService.findByEmail(username);
            expertService.confirmEmail(expertDto);
            session.setAttribute("expertDto", expertDto);
            return "redirect:/expert/dashboard";

        } else {
            CustomerDto customerDto = customerService.findByEmail(username);
            customerService.confirmEmail(customerDto);
            session.setAttribute("customerDto", customerDto);
            return "redirect:/customer/dashboard";
        }
    }
}

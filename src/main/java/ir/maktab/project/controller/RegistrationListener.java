package ir.maktab.project.controller;

import ir.maktab.project.data.dto.UserDto;
import ir.maktab.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Negin Mousavi
 */
@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final UserService userService;

    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(@NotNull OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserDto userDto = event.getUserDto();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(userDto, token);

        String recipientAddress = userDto.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "user_confirmation?token=" + token;
        String message = "confirmation" + "\r\n" + "http://localhost:8080/" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("home.service.provider.system@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}

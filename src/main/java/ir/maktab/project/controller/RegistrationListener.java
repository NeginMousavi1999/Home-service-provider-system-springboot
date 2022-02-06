package ir.maktab.project.controller;

import ir.maktab.project.data.dto.CustomerDto;
import ir.maktab.project.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
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
    private final CustomerService customerService;

    @Qualifier("messageSource")
    private final MessageSource messages;

    private final JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        CustomerDto customerDto = event.getCustomerDto();
        String token = UUID.randomUUID().toString();
        customerService.createVerificationToken(customerDto, token);

        String recipientAddress = customerDto.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/customer/confirm?token=" + token;
        String message = "confirmation" + "\r\n" + "http://localhost:8080" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("home.service.provider.system@gmail.com");
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}

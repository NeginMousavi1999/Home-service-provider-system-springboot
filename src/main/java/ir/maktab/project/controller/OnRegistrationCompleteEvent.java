package ir.maktab.project.controller;

import ir.maktab.project.data.dto.CustomerDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * @author Negin Mousavi
 */
@Getter
@Setter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final Locale locale;
    private final CustomerDto customerDto;

    public OnRegistrationCompleteEvent(CustomerDto customerDto, Locale locale, String appUrl) {
        super(customerDto);
        this.customerDto = customerDto;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}

package ir.maktab.project.controller;

import ir.maktab.project.data.dto.UserDto;
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
    private final UserDto userDto;

    public OnRegistrationCompleteEvent(UserDto userDto, Locale locale, String appUrl) {
        super(userDto);
        this.userDto = userDto;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}

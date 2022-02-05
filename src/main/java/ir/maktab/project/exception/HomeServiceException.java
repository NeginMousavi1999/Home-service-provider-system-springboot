package ir.maktab.project.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Negin Mousavi
 */
@Getter
@Setter
public class HomeServiceException extends RuntimeException {
    private int code;

    public HomeServiceException(String message) {
        super(message);
    }
}

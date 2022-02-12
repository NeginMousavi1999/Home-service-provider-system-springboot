package ir.maktab.project.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Negin Mousavi
 */
@Getter
@Setter
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}

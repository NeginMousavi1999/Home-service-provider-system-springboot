package ir.maktab.project.validation;

import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Negin Mousavi
 */
@Component
public class Validation {
    public boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!Pattern.matches(regex, email))
            throw new ValidationException("invalid email!");
        return true;
    }

    public void validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$";
        if (!Pattern.matches(regex, password))
            throw new ValidationException("the password must be at least 8 character, with a lower case, an upper case and no whitespace");
    }

    public void validateUserRole(UserRole expectRole, UserRole actualRole) {
        if (!expectRole.equals(actualRole))
            throw new ValidationException(String.format("your role is not %s!", expectRole.toString().toLowerCase()));
    }

    public void validateCorrectPassword(String oldPass, String newPass) {
        if (!oldPass.equals(newPass))
            throw new ValidationException("password is wrong!");
    }

    public boolean validateNewName(String name, List<String> allName) {
        if (allName.contains(name))
            throw new ValidationException("duplicate name!");
        return true;
    }

    public void validateCustomerCredit(double credit, double price) {
        if (credit < price)
            throw new ValidationException("you have not enough credit to pay!");
    }

    public void validateUserStatus(UserStatus expect, UserStatus actual) {
        if (!expect.equals(actual))
            throw new ValidationException(String.format("your status is not %s!", expect.toString().toLowerCase()));
    }
}

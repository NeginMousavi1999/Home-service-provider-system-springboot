package ir.maktab.project.validation;

import ir.maktab.project.data.enumuration.UserRole;
import ir.maktab.project.data.enumuration.UserStatus;
import ir.maktab.project.exception.HomeServiceException;
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
            throw new HomeServiceException("invalid email!");
        return true;
    }

    public boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$";
        if (!Pattern.matches(regex, password))
            throw new HomeServiceException("the password must be at least 8 character, with a lower case, an upper case and no whitespace");
        return true;
    }

    public boolean validateUserRole(UserRole expectRole, UserRole actualRole) {
        if (!expectRole.equals(actualRole))
            throw new HomeServiceException(String.format("your role is not %s!", expectRole.toString().toLowerCase()));
        return true;
    }

    public boolean validateCorrectPassword(String oldPass, String newPass) {
        if (!oldPass.equals(newPass))
            throw new HomeServiceException("password is wrong!");
        return true;
    }

    public boolean validateNewName(String name, List<String> allName) {
        if (allName.contains(name))
            throw new HomeServiceException("duplicate name!");
        return true;
    }

    public boolean validateCustomerCredit(double credit, double price) {
        if (credit < price)
            throw new HomeServiceException("you have not enough credit to pay!");
        return true;
    }

    public boolean validateUserStatus(UserStatus expect, UserStatus actual) {
        if (!expect.equals(actual))
            throw new HomeServiceException(String.format("your status is not %s!", expect.toString().toLowerCase()));
        return true;
    }
}

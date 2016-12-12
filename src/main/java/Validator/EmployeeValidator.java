package Validator;

import DTO.EmployeeDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return EmployeeDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        EmployeeDTO empl = (EmployeeDTO) o;

        String name = empl.getName();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty",
                "The name is required");

        String lastname = empl.getLastname();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty",
                "The lastname is required");

        String surname = empl.getSurname();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "surname.empty",
                "The surname is required");

        String login = empl.getLogin();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty",
                "The login is required");

        String password = empl.getPassword();
        if ((password.length()) > 20 || (password.length()) < 4) {
            errors.rejectValue("password", "password.notValid", "The password should be between 4 to 20");
        }
    }
}

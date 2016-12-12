package Validator;

import DTO.ApplicationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ApplicationValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ApplicationDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ApplicationDTO app = (ApplicationDTO) o;

        String name = app.getName();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty",
                "The name of the application is required");

        String description = app.getDescription();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty",
                "The description of the application is required");
    }
}

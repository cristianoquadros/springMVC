package com.crossover.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserLoginValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	User user = (User) target;
        if (user.getPassword()!=null && !user.getPassword().equals("12345")) {
            errors.rejectValue("password", "user.error.password.no_match");
        }
    }
}

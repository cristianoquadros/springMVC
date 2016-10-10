package com.crossover.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.crossover.domain.User;
import com.crossover.domain.UserLoginValidator;
import com.crossover.service.UserService;
import com.crossover.service.exception.UserAlreadyExistsException;

@Controller
public class UserCreateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateController.class);
    
    private final UserService userService;
    private final UserLoginValidator userLoginValidator;

    @Autowired
    public UserCreateController(UserService userService, UserLoginValidator userLoginValidator) {
        this.userService = userService;
        this.userLoginValidator = userLoginValidator;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userLoginValidator);
    }
    
    @GetMapping("/user_create")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/user_create")
    public String userSubmit(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user-create";
        }
        try {
            userService.save(user);
        } catch (UserAlreadyExistsException e) {
            LOGGER.debug("Tried to create user with existing id", e);
            result.reject("user.error.exists");
            return "user-create";
        }
        return "user-list";
    }    
}
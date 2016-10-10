package com.crossover.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.crossover.service.UserService;

@Controller
public class UserListController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserListController.class);
    private final UserService userService;

    @Autowired
    public UserListController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user_list")
    public String getListUsers(Model model) {
    	LOGGER.info("load user list");
    	
        model.addAttribute("users", userService.getList());
        return "user-list";
    }

}

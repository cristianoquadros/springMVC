package com.crossover.service;

import java.util.List;

import com.crossover.domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}

package com.crossover.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import com.crossover.domain.User;
import com.crossover.domain.UserLoginValidator;
import com.crossover.service.UserService;
import com.crossover.service.exception.UserAlreadyExistsException;

@RunWith(MockitoJUnitRunner.class)
public class UserCreateControllerTest {

    @Mock
    private UserService userService;
    @Mock
    private BindingResult result;
    @Mock
    private UserLoginValidator userLoginValidator;

    private UserCreateController userCreateController;

    @Before
    public void setUp() throws Exception {
        userCreateController = new UserCreateController(userService, userLoginValidator);
    }

    @Test
    public void shouldCreateUser_GivenThereAreNoErrors_ThenTheUserShouldBeSavedAndUserListViewDisplayed() {
        when(result.hasErrors()).thenReturn(false);        
        User user = new User();
        user.setId("id");
        user.setPassword("password");
        String view = userCreateController.userSubmit(user, result);
        assertEquals("There should be proper redirect", "user-list", view);
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).save(captor.capture());
        assertEquals(user.getId(), captor.getValue().getId());
        assertEquals(user.getPassword(), captor.getValue().getPassword());
    }

    @Test
    public void shouldCreateUser_GivenThereAreFormErrors_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(true);
        String view = userCreateController.userSubmit(new User(), result);
        verify(userService, never()).save(any(User.class));
        assertEquals("View name should be right", "user-create", view);
    }

    @Test
    public void shouldCreateUser_GivenThereAlreadyExistUserWithId_ThenTheFormShouldBeDisplayed() {
        when(result.hasErrors()).thenReturn(false);
        when(userService.save(any(User.class))).thenThrow(UserAlreadyExistsException.class);
        String view = userCreateController.userSubmit(new User(), result);
        verify(result).reject("user.error.exists");
        assertEquals("View name should be right", "user-create", view);
    }

}
package com.crossover;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/index.html"))
        		.andExpect(status().isOk())
                .andExpect(content().string(containsString("Go to user list")));
    }

    @Test
    public void greeting() throws Exception {
        mockMvc.perform(get("/user_list"))
        		.andExpect(status().isOk())
                .andExpect(content().string(containsString("List")));
    }

    @Test
    public void greetingWithUser() throws Exception {
        mockMvc.perform(get("/user_create"))
        		.andExpect(status().isOk())
                .andExpect(content().string(containsString("Form")));
    }

}

package com.thoughtworks.capacity.gtb.mvc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void register_right_name_and_password() throws Exception {

        User user = User.builder().username("Tom_5").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

    }

    @Test
    public void register_repeat_user() throws Exception {

        User user = User.builder().username("Tom_5").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
        assertThrows(Exception.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json));
            }
        },"user is exist");

    }

    @Test
    public void register_long_name_and_right_password() throws Exception {

        User user = User.builder().username("12345678901").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void register_short_name_and_right_password() throws Exception {

        User user = User.builder().username("12").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void register_blank_name_and_right_password() throws Exception {

        User user = User.builder().username("").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void register_special_characters_name_and_right_password() throws Exception {

        User user = User.builder().username("12@@@@@").password("12345").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }


    @Test
    public void register_right_name_and_long_password() throws Exception {

        User user = User.builder().username("Tom").password("1234567890123").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void register_right_name_and_short_password() throws Exception {

        User user = User.builder().username("Tom").password("1234").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void register_right_name_and_blank_password() throws Exception {

        User user = User.builder().username("Tom").password("").email("tom@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void register_right_name_and_password_with_wrong_email() throws Exception {

        User user = User.builder().username("Tom").password("22222").email("t@@@om@qq.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest());

    }
}
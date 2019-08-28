package com.sk.sinthoma.core.auth.controller;

import java.util.Optional;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.sinthoma.core.auth.model.User;
import com.sk.sinthoma.core.auth.service.UserService;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthenticationController.class)
@RunWith(SpringRunner.class)
public class AuthenticationControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
	given(this.userService.findById(1L)).willReturn(Optional.of(User.builder().username("john.doe").build()));

	given(this.vehicles.findById(2L)).willReturn(Optional.empty());

	given(this.vehicles.save(any(Vehicle.class))).willReturn(Vehicle.builder().name("test").build());

	doNothing().when(this.vehicles).delete(any(Vehicle.class));
    }

}

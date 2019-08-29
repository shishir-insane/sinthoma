package com.sk.sinthoma.core.auth.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
	mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void givenRequestOnCurrentUser_shouldFailWith403() throws Exception {
	mvc.perform(get("/me").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(403));
    }
    
    @Test
    @WithMockUser(username = "john.doe", roles = "USER")
    public void givenRequestOnCurrentUser_shouldFailWith200() throws Exception {
	mvc.perform(get("/me").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
    }

}

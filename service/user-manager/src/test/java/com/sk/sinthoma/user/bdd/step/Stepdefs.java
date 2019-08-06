package com.sk.sinthoma.user.bdd.step;

import com.sk.sinthoma.user.model.User;

import io.cucumber.java.en.Given;

public class Stepdefs {
    @Given("^I have users in my db$")
    public void I_have_cukes_in_my_belly(int cukes) throws Throwable {
        User user = new User();
        user.getFirstName();
    }
}
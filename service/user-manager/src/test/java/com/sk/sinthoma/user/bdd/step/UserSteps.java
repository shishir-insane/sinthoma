package com.sk.sinthoma.user.bdd.step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;

import com.sk.sinthoma.user.model.User;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.Response;

public class AddUserSteps extends AbstractSteps implements En {

    public AddUserSteps() {

	Given("user wants to create a new user with the following attributes", (DataTable userDt) -> {
	    testContext().reset();
	    List<User> userList = userDt.asList(User.class);
	    super.testContext().setPayload(userList.get(0));
	});

	When("user saves the new user {string}", (String testContext) -> {
	    String createUserUrl = "/sinthoma/user-manager/users";
	    executePost(createUserUrl);
	});

	Then("the save {string}", (String expectedResult) -> {
	    Response response = testContext().getResponse();

	    switch (expectedResult) {
	    case "IS SUCCESSFUL":
		assertThat(response.statusCode()).isIn(200, 201);
		break;
	    case "FAILS":
		assertThat(response.statusCode()).isBetween(400, 504);
		break;
	    default:
		fail("Unexpected error");
	    }
	});
    }
}
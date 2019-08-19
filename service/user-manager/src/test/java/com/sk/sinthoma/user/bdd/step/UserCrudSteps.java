/**
 * UserCrudSteps.java
 * user-manager
 * Copyright 2019 Shishir Kumar
 * 
 * Licensed under the GNU Lesser General Public License v3.0
 * Permissions of this license are conditioned on making available complete 
 * source code of licensed works and modifications under the same license 
 * or the GNU GPLv3. Copyright and license notices must be preserved. 
 * 
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code for 
 * the larger work.
 */
package com.sk.sinthoma.user.bdd.step;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.http.HttpMethod;

import com.sk.sinthoma.user.model.User;

import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.Response;

public class UserCrudSteps extends AbstractUserSteps implements En {

    private static final String USER_RESOURCE_URI = "/sinthoma/user-manager";

    public UserCrudSteps() {

	Given("client wants to (.*) with the following attributes", (String action, DataTable userDt) -> {
	    testContext().reset();
	    final List<User> userList = userDt.asList(User.class);
	    super.testContext().setPayload(userList.get(0));
	});
	
	Given("client wants to get a user with (.*)", (String attributes) -> {
	    testContext().reset();
	});

	When("(.*) call to endpoint (.*) with (.*) is made", (String method, String endpointUri, String attributes) -> {
	    if (StringUtils.equalsAnyIgnoreCase(method, HttpMethod.POST.toString())) {
		executePost(USER_RESOURCE_URI + endpointUri);
	    } else if (StringUtils.equalsAnyIgnoreCase(method, HttpMethod.GET.toString())) {
		executeGet(USER_RESOURCE_URI + endpointUri);
	    } else if (StringUtils.equalsAnyIgnoreCase(method, HttpMethod.PUT.toString())) {
		executePut(USER_RESOURCE_URI + endpointUri);
	    }
	});

	Then("the response (.*)", (String expectedResult) -> {
	    final Response response = testContext().getResponse();
	    switch (expectedResult.toUpperCase()) {
	    case "IS SUCCESSFUL":
		assertThat(response.statusCode()).isIn(200, 201);
		break;
	    case "FAILS":
		assertThat(response.statusCode()).isBetween(400, 504);
		break;
	    default:
		fail("Unexpected error");
		break;
	    }
	});

	And("it has (\\d+) user with parameter (.*) as (.*)", (Integer noOfUsers, String paramName, String paramValue) -> {
	    final Response response = testContext().getResponse();
	    if (noOfUsers == 1) {
		final User user = response.getBody().as(User.class);
		    assertThat(FieldUtils.readField(user, paramName, true)).isEqualTo(paramValue);
		}
	});
    }

    public Map<String, String> splitAttributesToMap(String attributes) {
	Map<String, String> attributesMap = null;
	if (StringUtils.isNotBlank(attributes)) {
	    attributesMap = new HashMap<>();
	    final List<String> convertedAttributesList = Stream.of(attributes.split(StringUtils.SPACE, -1))
		    .collect(Collectors.toList());
	    for (int i = 0; i < (convertedAttributesList.size() - 1); i += 2) {
		attributesMap.put(convertedAttributesList.get(i), convertedAttributesList.get(i + 1));
	    }
	}
	return attributesMap;
    }
}
package com.sk.sinthoma.user;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", plugin = { "pretty", "json:target/cucumber-report.json" }, glue = {
	"com.sk.sinthoma.user.bdd" })
public class RunCucumberTest {

}
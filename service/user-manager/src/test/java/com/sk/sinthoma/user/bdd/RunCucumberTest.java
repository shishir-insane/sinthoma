package com.sk.sinthoma.user.bdd;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = { "pretty", "json:target/cucumber-report.json" })
public class RunCucumberTest {

}
package com.revature.runner;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = {"src/test/java/com/revature/test/ClientPage.feature"},
		glue = {"com.revature.steps"},
		plugin = {"pretty"})
public class ClientPageRunner extends AbstractTestNGCucumberTests{
  @Test
  public void f() {
  }
}

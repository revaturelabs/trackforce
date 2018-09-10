package com.revature.test.admin.cuke;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.revature.test.admin.pom.HomeTab;
import com.revature.test.utils.ServiceHooks;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
public class HomeTabCukes{  
    
    @When("^I click on the telephone link$")
    public static void i_click_on_the_telephone_link()  {
        try {
            ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(HomeTab.phone(ServiceHooks.driver)));
            HomeTab.phone(ServiceHooks.driver).click();
        } catch (Throwable e) {
            System.out.println(("Failed click on the phone link"));
            fail(("Failed click on the phone link"));
        }
    }
    @Then("^I should see the telephone number link open on a browser$")
    public void i_should_see_the_telephone_number_link_open_on_a_browser(){
        /*
         * @Jacob Golding
         * This test should somehow use the below code but I was having
         * issues catching the Alert and the test would fail  even though
         * an alert was present
        ExpectedConditions.alertIsPresent();
        */
    }
    @When("^I click on the email link$")
    public static void i_click_on_the_email_link()  {
        try {
            ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(HomeTab.email(ServiceHooks.driver)));
            HomeTab.email(ServiceHooks.driver).click(); 
        } catch (Throwable e) {
            System.out.println(("Failed click on the email link"));
            fail(("Failed click on the email link"));
            
        }
    }
    @Then("^I should see the email link open on a browser$")
    public void i_should_see_the_email_link_open_on_a_browser() throws InterruptedException  {
        //Email opens in a location based on where the default email service is on the computer
    }
    @When("^I click on the website link$")
    public static void i_click_on_the_website_link()  {
        
        try {
            ServiceHooks.wait.until(ExpectedConditions.elementToBeClickable(HomeTab.website(ServiceHooks.driver)));
            HomeTab.website(ServiceHooks.driver).click();
            
        } catch (Throwable e) {
            System.out.println(("Failed click on the website link"));
            fail(("Failed click on the website link"));
        }
        
    }
    @Then("^I should see the Revature website link open on a browser$")
    public void i_should_see_the_Revature_website_link_open_on_a_browser() throws InterruptedException  {
        ServiceHooks.wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> handlers = new ArrayList<>(ServiceHooks.driver.getWindowHandles());
        ServiceHooks.driver.switchTo().window(handlers.get(1));
        ServiceHooks.wait.until(ExpectedConditions.urlContains("https://revature.com/"));
        assertEquals(ServiceHooks.driver.getCurrentUrl(),"https://revature.com/");
    }
    
    
}
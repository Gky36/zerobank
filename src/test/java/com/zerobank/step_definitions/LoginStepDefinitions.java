package com.zerobank.step_definitions;

import com.zerobank.pages.BasePage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Locale;

public class LoginStepDefinitions {

    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().findElement(By.id("signin_button")).click();

    }


    @And("User logins with username {string} and password {string}")
    public void userLoginsWithUsernameAndPassword(String arg0, String arg1) {
        loginPage.login(arg0,arg1);
    }

    @Then("the {} page should be displayed")
    public void thePageShouldBeDisplayed(String pageName) {
        String currentTitle = Driver.getDriver().getTitle();
        String selectedTab = basePage.selectedTab.getText();

        Assert.assertEquals("Page is not matching as expected", pageName,selectedTab);
        Assert.assertTrue("Title is not matching", currentTitle.toLowerCase().contains(pageName.toLowerCase()) );

    }

    @Then("Error message {} should be displayed")
    public void errorMessageShouldBeDisplayed(String expectedError) {
        Assert.assertEquals("Error message does not match", expectedError,loginPage.errorMessage.getText() );
    }
}
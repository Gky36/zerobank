package com.zerobank.step_definitions;

import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;

public class LoginStepDefinitions
{
    LoginPage loginPage = new LoginPage();

    @Given("the user on the login page")
    public void the_user_on_the_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().findElement(By.id("signin_button")).click();
    }
    @Then("the user enters credentials and logs in")
    public void the_user_enters_credentials_and_logs_in() {
        loginPage.userName.sendKeys("username");
        loginPage.userPassword.sendKeys("password");
        loginPage.loginButton.click();

        BrowserUtils.sleep(1);
        loginPage.resolveUnsecureConnections();
    }
}

package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage
{
    public LoginPage(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id="user_login")
    public WebElement userName;

    @FindBy(id="user_password")
    public WebElement userPassword;

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    public WebElement loginButton;

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);

    @FindBy (xpath = "//button[@id='details-button']")
    private WebElement advanced;

    @FindBy (xpath = "//a[@id='proceed-link']")
    private WebElement proceedLink;

    public void resolveUnsecureConnections(){
        wait.until(ExpectedConditions.visibilityOf(advanced));
        advanced.click();
        wait.until(ExpectedConditions.visibilityOf(proceedLink));
        proceedLink.click();

    }
}

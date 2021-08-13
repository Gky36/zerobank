package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountSummaryPage {

    public AccountSummaryPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//h2")
    public List<WebElement> accountTypes;

    @FindBy (xpath = "(//thead)[3]/tr/th")
    public List<WebElement> actualColumnNames;
}
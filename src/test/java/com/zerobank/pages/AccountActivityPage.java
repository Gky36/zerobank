package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AccountActivityPage {
    public AccountActivityPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(id = "aa_accountId")
    public WebElement showTransactionsAccount;

    @FindBy (xpath = "//thead/tr/th")
    public List<WebElement> accountColumnNames;

    public String selectedOption(){
        Select accountsDropDown = new Select(showTransactionsAccount);
        return accountsDropDown.getFirstSelectedOption().getText();
    }

    public List<WebElement> accountOptions(){
        Select accountsDropDown = new Select(showTransactionsAccount);
        List<WebElement> options = accountsDropDown.getOptions();
        return options;
    }

    @FindBy(linkText = "Find Transactions")
    public WebElement FindTransactions;

    @FindBy(id = "aa_fromDate")
    public WebElement fromDateInput;

    @FindBy(id = "aa_toDate")
    public WebElement toDateInput;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement findButton;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[1]")
    public List<WebElement> allDates;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[2]")
    public List<WebElement> allDescription;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[3]")
    public List<WebElement> allDeposit;

    @FindBy(xpath = "//div[@id='filtered_transactions_for_account']//tbody/tr//td[4]")
    public List<WebElement> allWithdrawal;

    @FindBy(id = "aa_description")
    public WebElement descriptionInput;

    @FindBy(id = "aa_type")
    public WebElement selectType;

    @FindBy(xpath = "(//table[@class='table table-condensed table-hover'])[2]/tbody/tr")
    public List<WebElement> rows;

    @FindBy (id = "aa_type")
    public WebElement type;

    public boolean columnBlank(int column){
        String check = "";
        for (int i = 1; i <= rows.size(); i++) {
            String locator = "(//table[@class='table table-condensed table-hover'])[2]/tbody/tr["+i+"]/td["+column+"]";
            check+=Driver.getDriver().findElement(By.xpath(locator)).getText();
        }
        check=check.trim();
        System.out.println(check);
        return check.isEmpty();
    }
    public void selectType (String selection){
        Select typeSelect = new Select(type);
        typeSelect.selectByVisibleText(selection);
    }

}
package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PayBillsPage {
    public PayBillsPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(id = "sp_payee")
    public WebElement payeeOptions;

    @FindBy(id = "sp_account")
    public WebElement accountOptions;

    @FindBy(id = "sp_amount")
    public WebElement amountInput;

    @FindBy(id = "sp_date")
    public WebElement dateInput;

    @FindBy(id = "sp_description")
    public WebElement descriptionInput;

    @FindBy(id = "pay_saved_payees")
    public WebElement payButton;

    @FindBy(id = "alert_content")
    public WebElement alertMessageBox;

    @FindBy(linkText = "Add New Payee")
    public WebElement AddNewPayee;

    @FindBy(id="add_new_payee")
    public WebElement addButton;

    @FindBy(linkText = "Purchase Foreign Currency")
    public WebElement PFC;

    @FindBy(id = "pc_calculate_costs")
    public WebElement calculateButton;


    @FindBy(id = "pc_currency")
    public WebElement pcCurrency;

    public boolean isContained (List<String> dataTable){
        boolean flag = true;
        Select currency = new Select(pcCurrency);
        List<WebElement> options = currency.getOptions();
        String currencyList="";
        for (WebElement option : options) {
            currencyList+=" "+option.getText();
        }
        for (String money : dataTable) {
            if (!(currencyList.contains(money))){
                flag = false;
            }
        }
        return flag;
    }

    public WebElement getYearStatement(String year){
        return Driver.getDriver().findElement(By.xpath("//div[@class='pull-left']//ul//li//a[.='"+year+"']"));
    }
    public List<WebElement> getAllStatements(String year){
        return Driver.getDriver().findElements(By.xpath("//div[@id='os_"+year+"']//tr//td[1]/a"));
    }
}
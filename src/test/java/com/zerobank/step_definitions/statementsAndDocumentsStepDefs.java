package com.zerobank.step_definitions;

import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import com.zerobank.utilities.FileUtility;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class statementsAndDocumentsStepDefs {
    PayBillsPage payBillsPage = new PayBillsPage();
    private File mostRecentFile;
    boolean fileFound;
    @When("the user selects the Recent Statements Year {int}")
    public void the_user_selects_the_Recent_Statements_Year(int year) {
        WebElement yearStatement = payBillsPage.getYearStatement(year + "");
        yearStatement.click();
        BrowserUtils.sleep(1);
    }

    @Then("{int} statements should be displayed for that {int}")
    public void statements_should_be_displayed_for_that_year(int numberOfStatements, int year) {
        List<WebElement> allStatements = payBillsPage.getAllStatements(year + "");
        Assert.assertEquals(numberOfStatements, allStatements.size());
    }

    @When("the user clicks on statement {string}")
    public void the_user_clicks_on_statement(String string) {
        BrowserUtils.sleep(1);
        Driver.getDriver().findElement(By.linkText(string)).click();
        BrowserUtils.sleep(3);
    }

    @Then("the downloaded file name should contain {string}")
    public void the_downloaded_file_name_should_contain(String fileName) {
        BrowserUtils.sleep(1);
        String downloadsFolder = System.getProperty("user.home") + "/Downloads/";
        mostRecentFile = FileUtility.getMostRecentFile(downloadsFolder);
        String name = mostRecentFile.getName();
        System.out.println(name);
        assertTrue(name.contains(fileName));
        fileFound=true;
    }

    @Then("the file type should be pdf")
    public void the_file_type_should_be_pdf() {
        assertTrue(mostRecentFile.getName().endsWith(".pdf"));
        if (fileFound) {
            mostRecentFile.delete();
        }
    }
}
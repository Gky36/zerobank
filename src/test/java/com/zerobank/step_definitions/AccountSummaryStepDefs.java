package com.zerobank.step_definitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class AccountSummaryStepDefs {

    AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
    @Then("Account Summary page should have the following account types")
    public void account_Summary_page_should_have_the_following_account_types(List<String> expectedAccounts) {
        List<String> actualAccounts = BrowserUtils.getElementsText(accountSummaryPage.accountTypes);
        Assert.assertEquals("Account types does not match",expectedAccounts,actualAccounts);
    }

    @Then("Credit Accounts table must have these columns")
    public void credit_Accounts_table_must_have_these_columns(List<String> expectedColumns) {
        List<String> actualColumns = BrowserUtils.getElementsText(accountSummaryPage.actualColumnNames);
        Assert.assertEquals("Column names do not match", expectedColumns,actualColumns);
    }

}
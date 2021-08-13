package com.zerobank.step_definitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FindTransactionStep {
    AccountActivityPage accountActivityPage = new AccountActivityPage();
    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {
        accountActivityPage.FindTransactions.click();
        BrowserUtils.sleep(1);
    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        accountActivityPage.fromDateInput.clear();
        accountActivityPage.fromDateInput.sendKeys(fromDate);
        accountActivityPage.toDateInput.clear();
        accountActivityPage.toDateInput.sendKeys(toDate);
    }

    @When("clicks search")
    public void clicks_search() {
        accountActivityPage.findButton.click();
        BrowserUtils.sleep(2);
    }

    @Then("results table should only show transactions dates between {string} to {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDate, String toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date fromD = dateFormat.parse(fromDate);
            Date afterD = dateFormat.parse(toDate);
            BrowserUtils.sleep(1);
            List<String> datesString = BrowserUtils.getElementsText(accountActivityPage.allDates);
            List<Date> actualDates = new ArrayList<>();
            for (String stringDate : datesString) {
                actualDates.add(dateFormat.parse(stringDate));
            }
            for (Date actualDate : actualDates) {
                boolean isBetween = (actualDate.after(fromD))||actualDate.equals(fromD) &&
                        actualDate.before(afterD) || actualDate.equals(afterD);
                Assert.assertTrue("Date is out of range", isBetween);
            }
        }catch (ParseException e){
            Assert.fail("Wrong date format, needs to be yyyy-MM-dd");
        }
    }
    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> datesString = BrowserUtils.getElementsText(accountActivityPage.allDates);
        List<Date> actualDates = new ArrayList<>();
        try {
            for (String stringDate : datesString) {
                actualDates.add(dateFormat.parse(stringDate));
            }
        } catch (ParseException e) {
            Assert.fail("Wrong date format, correct format should be: yyyy-MM-dd");
        }
        List<Date> sortedDate = new ArrayList<>(actualDates);

        sortedDate.sort(Collections.reverseOrder());
        for (int i = 0; i < sortedDate.size(); i++) {
            Assert.assertEquals(sortedDate.get(i), (actualDates.get(i)));
        }
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<String> datesString = BrowserUtils.getElementsText(accountActivityPage.allDates);
        List<Date> actualDates = new ArrayList<>();
        Date dateNotAcceptable = null;
        try {
            dateNotAcceptable = dateFormat.parse(date);
            for (String stringDate : datesString) {
                actualDates.add(dateFormat.parse(stringDate));
            }
        } catch (ParseException e) {
            Assert.fail("Wrong date format please follow this: yyyy-MM-dd");
        }
        for (Date actualDate : actualDates) {
            Assert.assertNotEquals(actualDate, dateNotAcceptable);
        }
    }
}
package com.sportsdirect.steps;

import com.sportsdirect.BaseTest;
import com.sportsdirect.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddItemsIntoBagSteps /*extends BaseTest*/ {

    private HomePage homePage;
    private ToolBarPage toolBarPage;
    private SearchResultsPage searchResultsPage;
    private SelectedItemPage selectedItemPage;
    private BagPage bagPage;
    private BaseTest baseTest;

    private int firstProductIncremented;

    public AddItemsIntoBagSteps(){
        baseTest = new BaseTest();

        toolBarPage = new ToolBarPage(baseTest.driver);

        firstProductIncremented = 0;
    }

    @Given("^I navigate to the home page$")
    public void iNavigateToTheHomePage() throws Throwable {
        baseTest.driver.get(baseTest.urlHome);
        homePage = new HomePage(baseTest.driver);
    }

    @And("^I ensure Bag is empty$")
    public void iEnsureBagIsEmpty() throws Throwable {
        if (toolBarPage.itemsInBag() != 0){
            throw new Exception("Bag is not empty, it has: " + toolBarPage.itemsInBag() + " items");
        }
    }

    @And("^I submit \"([^\"]*)\" as a search criteria from Home page$")
    public void iSubmitAsASearchCriteriaFromHomePage(String searchCriteria) throws Throwable {
        searchResultsPage = homePage.searchItemAndSubmit(searchCriteria);
    }

    @Given("^To avoid advertisement I go directly to Search page$")
    public void toAvoidAdvertisementIGoDirectlyToSearchPage() throws Throwable {
        baseTest.driver.get(baseTest.urlSearch);
        searchResultsPage = new SearchResultsPage(baseTest.driver);
    }

    @When("^I submit \"([^\"]*)\" as a search criteria$")
    public void iSubmitAsASearchCriteria(String searchCriteria) throws Throwable {
        toolBarPage.searchItemAndSubmit(searchCriteria);
    }

    @And("^I select first item in the list$")
    public void iSelectFirstItemInTheList() throws Throwable {
        selectedItemPage = searchResultsPage.selectFirstItemInProductList();
    }

    @And("^I select first size for the item$")
    public void iSelectFirstSizeForTheItem() throws Throwable {
        selectedItemPage.selectFirstAvailableSizeInList();
    }

    @And("^I add the item to the Bag$")
    public void iAddTheItemToTheBag() throws Throwable {
        selectedItemPage.clickAddToBagButton();
        baseTest.itemsAddedToBagDuringTest++;
    }

    @And("^I wait to advertisements to be loaded and I refresh the page$")
    public void iWaitToAdvertisementsToBeLoadedAndIRefreshThePage() throws Throwable {
        new WebDriverWait(baseTest.driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.id("CloseNewsLetterModal")));
        toolBarPage.reloadPage();
    }

    @And("^I open Bag page$")
    public void iOpenBagPage() throws Throwable {
        bagPage = toolBarPage.openBagPage();
    }

    @Then("^I validate quantity and price data correctness$")
    public void iValidateQuantityAndPriceDataCorrectness() throws Throwable {
        bagPage.checkTotalPriceCorrectnessWhereFirstItemIncremented(baseTest.itemsAddedToBagDuringTest, firstProductIncremented);
    }

    @And("^I increase first item '(\\d+)' times$")
    public void iIncreaseFirstItemTimes(int increment) throws Throwable {
        bagPage.addFirstInstances(increment);
        firstProductIncremented += increment;
    }

    @And("^I update Bag$")
    public void iUpdateBag() throws Throwable {
        bagPage.clickUpdateBagBtn();
    }


}

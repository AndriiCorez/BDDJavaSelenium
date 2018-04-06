package com.sportsdirect.pages;

import com.sportsdirect.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * As far as Tool bar is the same for all pages it is created as separate object
 * which can be used in cases where where the action is made only from the tool bar
 */
public class ToolBarPage extends BasePage{

    public ToolBarPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "bagQuantity")
    private WebElement bagItemsCounter;

    @FindBy(id = "txtSearch")
    private WebElement searchField;

    @FindBy(id = "aBagLink")
    private WebElement bagLink;

    //submit() method is not working in this case. Send enter key is used therefore
    public SearchResultsPage searchItemAndSubmit(String itemName) {
        searchField.sendKeys(itemName);
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public BagPage openBagPage(){
        bagLink.click();
        return new BagPage(driver);
    }

    public int itemsInBag(){
        return Integer.parseInt(bagItemsCounter.getText());
    }

}

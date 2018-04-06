package com.sportsdirect.pages;

import com.sportsdirect.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);

        PageFactory.initElements(driver, this);

        if(!HOME_PAGE_TITLE.equals(driver.getTitle())){
            String url = driver.getCurrentUrl();
            throw new IllegalStateException("This is not home page! " + url);
        }
    }

    @FindBy(id = "txtSearch")
    private WebElement searchField;

    @FindBy(id = "bagQuantity")
    private WebElement bagItemsCounter;

    public SearchResultsPage searchItemAndSubmit(String itemName)
    {
        searchField.sendKeys(itemName);
        searchField.submit();
        return new SearchResultsPage(driver);
    }

}

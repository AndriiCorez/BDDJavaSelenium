package com.sportsdirect.pages;

import com.sportsdirect.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage extends BasePage{

    public SearchResultsPage(WebDriver driver){
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "productdescriptionname")
    private WebElement firstAvailableItem;

    public SelectedItemPage selectFirstItemInProductList(){
        firstAvailableItem.click();
        return new SelectedItemPage(driver);
    }



}

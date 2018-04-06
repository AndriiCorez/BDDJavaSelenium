package com.sportsdirect.pages;

import com.sportsdirect.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectedItemPage extends BasePage {

    public SelectedItemPage(WebDriver driver) {
        super(driver);

        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[contains(@id, 'ctl00_ancLink')]")
    private WebElement firstAvailableSize;

    @FindBy(id = "txtSearch")
    private WebElement searchField;

    @FindBy(className = "addToBag")
    private WebElement addToBagBtn;

    @FindBy(xpath = "//a[@class='btn btn-default btnNo']")
    private WebElement magazineOfferCanelation;

    public SelectedItemPage selectFirstAvailableSizeInList() throws InterruptedException {
        firstAvailableSize.click();
        return this;
    }

    public SelectedItemPage clickAddToBagButton(){
        addToBagBtn.click();
        try{
            magazineOfferCanelation.click(); //sometimes magazine offer appears
        }catch (Exception ex){}

        return this;
    }


}

package com.sportsdirect.pages;

import com.sportsdirect.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BagPage extends BasePage {

    public BagPage(WebDriver driver){
        super(driver);

        PageFactory.initElements(driver, this);

        itemPrice = new ArrayList<>();
        itemTotalPrice = new ArrayList<>();
    }

    private List<Float> itemPrice;
    private List<Float> itemTotalPrice;

    @FindBy(xpath = "//*[@id=\'dnn_ctr1628848_ViewBasket_BasketDetails_gvBasketDetails\']/table/tbody/tr/td[4]/div/a[2]")
    private WebElement firstItemPlusBtn;

    @FindBy(xpath = "//*[@id=\'dnn_ctr1628848_ViewBasket_BasketDetails_lbtnUpdateQtyAndVariants\']")
    private WebElement updateBagBtn;

    public BagPage addFirstInstances(int numberOfInstances)
    {
        for (int i = 0; i < numberOfInstances; i++) {
            firstItemPlusBtn.click();
        }
        return this;
    }

    public BagPage clickUpdateBagBtn(){
        updateBagBtn.click();
        return this;
    }

    public void checkTotalPriceCorrectnessWhereFirstItemIncremented(int itemsAddedToBag, int firstItemIncremented){

        final int oneItem = 1;

        populateItemsPrices(itemsAddedToBag);

        for(int i = 0; i < itemsAddedToBag; i++){
            if(i == 0) {
                Assert.assertTrue(validateTotalPrices(itemPrice.get(i), itemTotalPrice.get(i), ++firstItemIncremented),
                        "Incorrect total price:" + itemTotalPrice.get(i) + " for first item");
            } else
                Assert.assertTrue(validateTotalPrices(itemPrice.get(i), itemTotalPrice.get(i), oneItem),
                        "Incorrect total price:" + itemTotalPrice.get(i) + " for item " + ++i);
        }
    }

    private void populateItemsPrices(int itemsAddedToBag){
        List<WebElement> prices = new ArrayList<>();;
        List<WebElement> totalPrices = new ArrayList<>();;
        String priceXpathBeginning = "//*[@id=\'dnn_ctr1628848_ViewBasket_BasketDetails_gvBasketDetails\']/table/tbody/tr[";
        String priceXpathEnding = "]/td[5]/span[2]";

        String totalPriceXpathBeginning = "//*[@id='dnn_ctr1628848_ViewBasket_BasketDetails_gvBasketDetails']/table/tbody/tr[";
        String totalPriceXpathEnding = "]/td[6]/span[2]";

        for (int i = 1; i < itemsAddedToBag + 1; i++){
            prices.add(driver.findElement(By.xpath(priceXpathBeginning + i + priceXpathEnding)));
            totalPrices.add(driver.findElement(By.xpath(totalPriceXpathBeginning + i + totalPriceXpathEnding)));
        }

        for (WebElement element : prices){
            itemPrice.add(getNumericValueFromString(element.getText()));
        }

        for (WebElement element : totalPrices){
            itemTotalPrice.add(getNumericValueFromString(element.getText()));
        }
    }

    private boolean validateTotalPrices(float price, float totalPrice, int numberOfItems){
        return price*numberOfItems == totalPrice;
    }

    private float getNumericValueFromString(String value){
        Pattern pattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        Matcher matcher = pattern.matcher(value);
        String s = "";
        while (matcher.find()){
            s = matcher.group();
        }
        return Float.parseFloat(s);
    }

}

package com.sportsdirect;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Duration;
import org.openqa.selenium.support.ui.Sleeper;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

   public static final String HOME_PAGE_TITLE = "SportsDirect.com - The UK's No 1 Sports Retailer";
   public static final int DEFAULT_WAIT_FOR_PAGE_LOAD = 10;
   public static final int DEFAULT_WAIT_FOR_SCRIPT = 1000;
   protected final WebDriver driver;

   public BasePage(WebDriver driver){
      this.driver = driver;
      driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
      driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
   }

   public void reloadPage(){
      driver.navigate().refresh();
   }

   public String getPageTitle() {
      return driver.getTitle();
   }

   public void waitForPageLoadComplete(){
      final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      do{
         try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(Long.valueOf(DEFAULT_WAIT_FOR_SCRIPT), TimeUnit.MILLISECONDS));
         } catch (InterruptedException ex){
            //TBD ADD LOGGING HERE!!!
         }

      }while (!javascriptExecutor.executeScript("return document.readyState").equals("complete"));
   }

   public void waitForAjaxComplete(){
      final JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
      do{
         try {
            Sleeper.SYSTEM_SLEEPER.sleep(new Duration(Long.valueOf(DEFAULT_WAIT_FOR_SCRIPT), TimeUnit.MILLISECONDS));
         } catch (InterruptedException ex){
            //TBD ADD LOGGING HERE!!!
         }

      }while (!(Boolean) javascriptExecutor.executeScript("return jQuery.active == 0"));
   }
}

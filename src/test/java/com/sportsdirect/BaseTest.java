package com.sportsdirect;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private DesiredCapabilities caps;
    private String isPhantomJS;
    protected FirefoxProfile firefoxProfile;
    public WebDriver driver;
    public String urlHome;
    public String urlSearch;
    public static int itemsAddedToBagDuringTest = 0;

    public BaseTest(){
        if(urlHome == null && urlSearch == null && isPhantomJS == null){
            initializeProperties();
        }
    }

    private void initializeProperties(){
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("integration-tests.properties");

        try{
            prop.load(stream);
        } catch (IOException e){
            //TBD ADD LOGGING HERE!!!
        }

        urlHome = prop.getProperty("integration-tests.urlHome");
        urlSearch = prop.getProperty("integration-tests.urlSearch");
        isPhantomJS = prop.getProperty("integration-tests.isPhantomJS");

        if(isPhantomJS.toLowerCase().equals("true")){
            caps = DesiredCapabilities.phantomjs();
            caps.setJavascriptEnabled(true);
            caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    "./src/test/resources/drivers/phantomjs.exe");
            driver = new PhantomJSDriver(caps);
        }else{
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(BasePage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(BasePage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(BasePage.DEFAULT_WAIT_FOR_PAGE_LOAD, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass(){
        driver.close();
        driver.quit();
    }
}

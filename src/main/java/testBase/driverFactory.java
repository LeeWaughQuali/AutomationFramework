package testBase;

import org.openqa.selenium.WebDriver;

public class driverFactory {

    private driverFactory(){

    }
    private static driverFactory instance = new driverFactory();

    public static driverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public void setDriver(WebDriver driverParm){
        driver.set(driverParm);
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    public void closeBrowser(){
        driver.get().quit();
        driver.remove();
    }
}

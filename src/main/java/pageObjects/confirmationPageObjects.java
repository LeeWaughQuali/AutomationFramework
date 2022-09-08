package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testBase.driverFactory;

public class confirmationPageObjects {

    By confirmMsg = By.xpath("//div[@id='common_alert']/following-sibling::h1");

    public String validateConMsg(){
        WebDriver dI = driverFactory.getInstance().getDriver();

        String text = dI.findElement(confirmMsg).getText();

        return text;
    }
}

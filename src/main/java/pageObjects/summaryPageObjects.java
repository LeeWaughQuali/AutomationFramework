package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testBase.driverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class summaryPageObjects {

    By inputted_infoVal = By.xpath("//div[@class='col-sm-7']");
    By inputted_infoName = By.xpath("//div[@class='col-sm-5']");
    By email_input = By.id("booking_guest_attributes_e_mail");
    By lastName_input = By.id("booking_guest_attributes_last_name");
    By firstName_input = By.id("booking_guest_attributes_first_name");
    By phone_input = By.id("booking_guest_attributes_phone_number");
    String paymentMethod_text = "//div[@class='form-group guarantee-methods']/div/label";
    String payment_radioBtn = "/input";
    By agree_terms = By.id("booking_agreed");
    By create_bookingBtn = By.xpath("//input[@value='Create Booking']");

    public void createBooking(){
        WebDriver dI = driverFactory.getInstance().getDriver();
        dI.findElement(agree_terms).click();
        dI.findElement(create_bookingBtn).click();

    }
    public void paymentSelection(String PaymentMethod){
        WebDriver dI = driverFactory.getInstance().getDriver();
        List<WebElement> options = dI.findElements(By.xpath(paymentMethod_text));
        for(int i=0;i<options.size();i++){
            String text = dI.findElements(By.xpath(paymentMethod_text)).get(i).getText();
            if(text.equalsIgnoreCase(PaymentMethod)){
                dI.findElement(By.xpath("("+ paymentMethod_text + ")["
                        + (i+1) + "]" + payment_radioBtn)).click();
            }
        }
    }

    public void personalDetailsInput(String Email, String LastName, String FirstName, String PhoneNo){
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(email_input).sendKeys(Email);
        dI.findElement(lastName_input).sendKeys(LastName);
        dI.findElement(firstName_input).sendKeys(FirstName);
        dI.findElement(phone_input).sendKeys(PhoneNo);
    }
    public Map<String, String> validateInputs(){
        WebDriver dI = driverFactory.getInstance().getDriver();

        Map<String, String> inputs = new HashMap<>();
        List<WebElement> names = dI.findElements(inputted_infoName);
        List<WebElement> values = dI.findElements(inputted_infoVal);
        for(int i=0;i<names.size();i++){
            String name = dI.findElements(inputted_infoName).get(i).getText();
            String value = dI.findElements(inputted_infoVal).get(i).getText();
            inputs.put(name, value);
        }
        return inputs;
    }

}

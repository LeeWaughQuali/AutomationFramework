package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import testBase.driverFactory;

public class ccPaymentPageObjects {

    By card_number = By.id("cardNumber");

    By cardType_dropdown = By.id("credit_card_collect_purchase_brand");

    By cardExpireMonth_dropdown = By.id("cardExpirationMonth");

    By cardExpireYear_dropdown = By.id("cardExpirationYear");

    By card_address = By.id("credit_card_collect_purchase_address");

    By card_zipCode = By.id("credit_card_collect_purchase_zip");

    By card_city = By.id("credit_card_collect_purchase_city");

    By card_state = By.id("credit_card_collect_purchase_state");

    By cardCountry_dropdown = By.id("credit_card_collect_purchase_country");
    By payBtn = By.xpath("//button[@type='submit']");

    public void cardInfoInput(String CardNumber, String CardBrand, String CardExpiryMonth, String CardExpiryYear){
        WebDriver dI = driverFactory.getInstance().getDriver();
        Select typeDropdown = new Select(dI.findElement(cardType_dropdown));
        Select exMonthDropdown = new Select(dI.findElement(cardExpireMonth_dropdown));
        Select exYearDropdown = new Select(dI.findElement(cardExpireYear_dropdown));

        dI.findElement(card_number).sendKeys(CardNumber);
        typeDropdown.selectByVisibleText(CardBrand);
        exMonthDropdown.selectByVisibleText(CardExpiryMonth);
        exYearDropdown.selectByVisibleText(CardExpiryYear);
    }

    public void billingInfoInput(String Address, String ZipCode, String City, String State, String Country){
        WebDriver dI = driverFactory.getInstance().getDriver();
        Select countryDropdown = new Select(dI.findElement(cardCountry_dropdown));

        dI.findElement(card_address).sendKeys(Address);
        dI.findElement(card_zipCode).sendKeys(ZipCode);
        dI.findElement(card_city).sendKeys(City);
        dI.findElement(card_state).sendKeys(State);

        countryDropdown.selectByVisibleText(Country);

    }

    public void payBooking(){
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(payBtn).click();
    }

}

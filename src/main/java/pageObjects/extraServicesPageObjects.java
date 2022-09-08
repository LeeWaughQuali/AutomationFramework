package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testBase.driverFactory;

import java.util.List;

public class extraServicesPageObjects {

    By services_frame = By.xpath("//iframe[@class='clock_pms_iframe']");

    By service_name = By.xpath("//div[@class='col-sm-5 h4']/div[@class='form-control-static']");

    By service_price = By.xpath("//div[@class='col-sm-5 h4 text-right']/div[@class='form-control-static']");

    By service_quantity = By.xpath("//input[@type='number']");

    By submit_btn = By.xpath("(//input[@name='commit'])[1]");

    public void submitAddOns(){
        WebDriver dI = driverFactory.getInstance().getDriver();

        WebElement button = dI.findElement(submit_btn);
        ((JavascriptExecutor)dI).executeScript("arguments[0].click();", button);
    }

    public Double SelectAddOn(String ServiceName, String Qty){
        WebDriver dI = driverFactory.getInstance().getDriver();
        //dI.switchTo().frame(dI.findElement(services_frame));

        String value = null;
        Double addOnValue= 0.00;

        List<WebElement> addOns = dI.findElements(service_name);
        for(int i=0;i< addOns.size();i++){
            String text = dI.findElements(service_name).get(i).getText();
            if(text.equalsIgnoreCase(ServiceName)){
                dI.findElements(service_quantity).get(i).sendKeys(Qty);

                value = dI.findElements(service_price).get(i).getText();
                break;
            }
        }
        if(value.contains("Nights")){
            Double x = Double.valueOf(value.split(" ")[0]);
            Double y = Double.valueOf(value.split(" ")[3]);
            addOnValue = x*y;


    }else{
            addOnValue = Double.valueOf(value.split(" ")[0]);

        }
        Double pricePaid = addOnValue * Double.valueOf(Qty);


        return pricePaid;

    }


}

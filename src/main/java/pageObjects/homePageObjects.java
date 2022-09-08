package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testBase.driverFactory;

public class homePageObjects {
    By arrival_box = By.id("date-start");
    By day_picker = By.xpath("//td[@class='day']");
    By calender_month = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']");
    By month_picker = By.className("next");
    By nights_box = By.id("to-place");
    By adults_count = By.name("wbe_product[adult_count]");
    By children_count = By.name("wbe_product[children_count]");
    By bookNow_button = By.name("commit");

    public String pickArrivalDate(String day, String month){
        //Input shorthand months eg Jan, Feb, Oct, Nov, Dec ...
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(arrival_box).click();
        while(!dI.findElement(calender_month).getText().contains(month)){
            dI.findElement(month_picker).click();
        }
        String year = dI.findElement(calender_month).getText().split(" ")[1];
        for(int i=0;i<dI.findElements(day_picker).size();i++){
            String text = dI.findElements(day_picker).get(i).getText();
            if(text.equalsIgnoreCase(day)){
                dI.findElements(day_picker).get(i).click();
                break;
            }
        }
        String date = day +" "+ month +" "+ year;
        return date;
    }
    public String pickNoNights(String NoNights){
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(nights_box).click();
        dI.findElement(nights_box).clear();
        dI.findElement(nights_box).sendKeys(NoNights);
        return NoNights;

    }
    public String pickNoAdults(String NoAdults){
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(adults_count).click();
        dI.findElement(adults_count).clear();
        dI.findElement(adults_count).sendKeys(NoAdults);
        return NoAdults;
    }
    public String pickNoChildren(String NoChildren){
        WebDriver dI = driverFactory.getInstance().getDriver();

        dI.findElement(children_count).click();
        dI.findElement(children_count).clear();
        dI.findElement(children_count).sendKeys(NoChildren);
        return NoChildren;
    }
    public void clickSubmit(){
        WebDriver dI = driverFactory.getInstance().getDriver();
        dI.findElement(bookNow_button).click();
    }
}

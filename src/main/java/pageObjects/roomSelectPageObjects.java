package pageObjects;

import org.openqa.selenium.*;
import testBase.driverFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class roomSelectPageObjects {

    By rooms_frame = By.xpath("//iframe[@class='clock_pms_iframe']");

    By available_roomNames = By.xpath("//div[@class='col-md-12']/h2");
    String available_rooms = "//div[@class='bookable-container bookable-location-3864']";

    String package_prices = "/div/div/table/tbody/tr/td[@class='text-right hidden-xs']/h4[@style='white-space: nowrap;']";

    String select_room = "/div/div/table/tbody/tr/td[@class='hidden-xs nowrap']/span/a";
    String rate_name = "h4[contains(text(), 'Rate')]";




    public List<String> SelectRoomPackage(String RoomName) {
        WebDriver dI = driverFactory.getInstance().getDriver();

        //switch to frame using rooms_frame
        dI.switchTo().frame(dI.findElement(rooms_frame));
        List<String> info = new ArrayList<>();
        //grab all rooms into list and iterate through to find desired one
        List<WebElement> rooms = dI.findElements(available_roomNames);
        for (int i = 0; i < rooms.size(); i++) {
            String text = dI.findElements(available_roomNames).get(i).getText();
            if (text.equalsIgnoreCase(RoomName)) {
                List<Double> pricesList = new ArrayList<Double>();
                List<WebElement> pricePlaceHolder = dI.findElements(By.xpath(available_rooms + "[" + (i + 1) + "]" + package_prices));
                for (int j = 0; j < pricePlaceHolder.size(); j++) {
                    String pricesValue = dI.findElements(By.xpath(available_rooms
                            + "[" + (i + 1) + "]" + package_prices)).get(j).getText();
                    //System.out.println(pricesValue);
                    Double values = null;
                    if(pricesValue.contains(",")){
                    values = Double.valueOf(pricesValue.split(" ")[0].replace(",", ""));
                    //System.out.println(values);

                }else{
                        values = Double.valueOf(pricesValue.split(" ")[0]);
                    }
                    pricesList.add(values);
                }

                //System.out.println(pricesList.size());

                int indexMaxPrice = pricesList.indexOf(Collections.max(pricesList));
                Double maxPrice = pricesList.get(indexMaxPrice);
                String rate = dI.findElement(By.xpath("(" + available_rooms
                        + "[" + (i + 1) + "]//child::" + rate_name + ")[" + (indexMaxPrice + 1) + "]")).getText();
                //System.out.println(indexMaxPrice);
                dI.findElement(By.xpath("(" + available_rooms
                        + "[" + (i + 1) + "]" + select_room + ")[" + (indexMaxPrice + 1) + "]")).click();
                info = Arrays.asList(RoomName, Double.toString(maxPrice), rate);
                break;
            }
        }


        return info;
    }
}

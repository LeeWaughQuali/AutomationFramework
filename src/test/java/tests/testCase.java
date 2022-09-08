package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.TestBase;
import testBase.myLogger;
import testBase.commonMethods;

import java.util.List;
import java.util.Map;


public class testCase extends TestBase {

    homePageObjects homePage = new homePageObjects();
    roomSelectPageObjects roomSelectPage = new roomSelectPageObjects();
    extraServicesPageObjects addOnsPage = new extraServicesPageObjects();
    summaryPageObjects summaryPage = new summaryPageObjects();
    ccPaymentPageObjects paymentPageObjects = new ccPaymentPageObjects();
    commonMethods cM = new commonMethods();
    confirmationPageObjects confirmationPageObjects = new confirmationPageObjects();



    @Test
    public void testCase1() throws InterruptedException {
        myLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());

        //Pick booking inputs on first page
        String arrivalDate = homePage.pickArrivalDate("30", "Nov");
        String noNights = homePage.pickNoNights("4");
        String noAdults = homePage.pickNoAdults("2");
        String noChildren = homePage.pickNoChildren("3");
        homePage.clickSubmit();

        //Select room package
        List<String> roomInfo = roomSelectPage.SelectRoomPackage("Deluxe Appartment");

        //Select add-ons
        Double addOnOne = addOnsPage.SelectAddOn("Business Services","2");
        Double addOnTwo = addOnsPage.SelectAddOn("Airport Transfer (one way)", "1");
        Double addOnTotal = addOnOne + addOnTwo;
        addOnsPage.submitAddOns();
        //Validate all details held on summary page with assertions
        Double Total = addOnTotal + Double.valueOf(roomInfo.get(1));
        Map<String, String> map = summaryPage.validateInputs();

        Assert.assertEquals(arrivalDate, map.get("Arrival"));
        Assert.assertEquals(noNights, map.get("Stay"));
        Assert.assertEquals(noAdults, map.get("Adults"));
        Assert.assertEquals(noChildren, map.get("Children"));
        Assert.assertEquals(roomInfo.get(0), map.get("Room Type"));
        Assert.assertEquals(Double.valueOf(roomInfo.get(1)),Double.valueOf(cM.parseString(map.get("Rooms"))));
        Assert.assertEquals(addOnTotal, Double.valueOf(cM.parseString(map.get("Extra Services"))));
        Assert.assertEquals(Total, Double.valueOf(cM.parseString(map.get("Total"))));
        //add details and payment info
        summaryPage.personalDetailsInput("test@testemail.com", "Test", "Susan", "012345678");
        summaryPage.paymentSelection("Credit Card");
        summaryPage.createBooking();
        //payment info to confirm and pay booking
        paymentPageObjects.cardInfoInput("4111111111111111","VISA","05", "2025");
        paymentPageObjects.billingInfoInput("16 Test Lane", "TT12 7ST", "Testville", "Testas", "United Kingdom");
        paymentPageObjects.payBooking();
        //check booking confirmation is displayed on screen
        String confirmMsg = confirmationPageObjects.validateConMsg();
        Assert.assertEquals(confirmMsg, "Thank you for your booking!");

        myLogger.endTestCase(new Throwable().getStackTrace()[0].getMethodName());




    }
}

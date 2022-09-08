package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.ExtentSetup;
import testBase.driverFactory;
import testBase.extentFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class listenersReport implements ITestListener {

    static ExtentReports report;
    ExtentTest test;
    public void onTestStart(ITestResult result){
        test = report.createTest(result.getMethod().getMethodName());
        extentFactory.getInstance().setExtent(test);
    }

    public void onTestSuccess(ITestResult result){
        extentFactory.getInstance().getExtent().log(Status.PASS, "Test Case: "
                +result.getMethod().getMethodName()+ " is Passed.");
        extentFactory.getInstance().removeExtentObject();
    }

    public void onTestFailure(ITestResult result){
        extentFactory.getInstance().getExtent().log(Status.FAIL, "Test Case: "
                +result.getMethod().getMethodName()+ " is Failed.");
        extentFactory.getInstance().getExtent().log(Status.FAIL, result.getThrowable());

        File src = ((TakesScreenshot)driverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String screenshotPath = System.getProperty("user.dir")+
                "/Reports/Screenshots/"+actualDate+".jpeg";
        File dest = new File(screenshotPath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            extentFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshotPath,
                    "Test case failure screenshot");
        } catch (IOException e) {
            e.printStackTrace();
        }
        extentFactory.getInstance().removeExtentObject();
    }

    public void onTestSkipped(ITestResult result){
        extentFactory.getInstance().getExtent().log(Status.SKIP, "Test Case: "
                +result.getMethod().getMethodName()+ " is Skipped");
        extentFactory.getInstance().removeExtentObject();
    }

    public void onStart(ITestContext context){
        try {
            report = ExtentSetup.setupExtentReport();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void onFinish(ITestContext context){
        report.flush();
    }


}

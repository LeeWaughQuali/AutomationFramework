package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import utilities.propertiesOperations;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentSetup {
    static ExtentReports extent;

    public static ExtentReports setupExtentReport() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date date = new Date();
        String actualDate = format.format(date);

        String reportPath = System.getProperty("user.dir")+
                "/Reports/ExecutionReport_"+actualDate+".html";

        ExtentSparkReporter sparkReport = new ExtentSparkReporter(reportPath);

        extent = new ExtentReports();
        extent.attachReporter(sparkReport);

        sparkReport.config().setDocumentTitle("TestReport");
        sparkReport.config().setTheme(Theme.STANDARD);
        sparkReport.config().setReportName("E2E Summary Report");

        extent.setSystemInfo("Executed on Environment: ", propertiesOperations.getPropertyValueByKey("url"));
        extent.setSystemInfo("Executed on Browser: ", propertiesOperations.getPropertyValueByKey("browser"));
        extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
        extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));

        return extent;
    }
}

package testBase;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.propertiesOperations;



public class TestBase {

    browserFactory bf = new browserFactory();

    @BeforeMethod
    public void LaunchApplication() throws Exception {
        String browser = propertiesOperations.getPropertyValueByKey("browser");
        String url = propertiesOperations.getPropertyValueByKey("url");
        WebDriver driverInstance = bf.createBrowserInstance(browser);
        driverFactory.getInstance().setDriver(driverInstance);

        WebDriver driver = driverFactory.getInstance().getDriver();

        driver.manage().window().maximize();

        driver.navigate().to(url);
    }
    @AfterMethod
    public void tearDown(){
        driverFactory.getInstance().closeBrowser();
    }

}

package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class browserFactory {

    public WebDriver createBrowserInstance(String browser) {

        WebDriver driver = null;

        if (browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions foptions = new FirefoxOptions();
            foptions.addArguments("--private");
            driver = new FirefoxDriver(foptions);
        } else if (browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver();

            driver = new InternetExplorerDriver();
        }
        return driver;
    }
}
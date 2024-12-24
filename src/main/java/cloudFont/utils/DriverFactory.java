package cloudFont.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	private static WebDriver driver;

	public static WebDriver getDriver(String browser) {
		if (driver == null) {
			switch (browser.toLowerCase()) {
			case "chrome":

				driver = new ChromeDriver();
				break;
			case "firefox":

				driver = new FirefoxDriver();
				break;
			case "edge":

				driver = new EdgeDriver();
				break;
			default:
				throw new IllegalArgumentException("Browser not supported: " + browser);
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		return driver;
	}

	public static WebDriver getDriver() {
		return getDriver("chrome");
	}

	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}

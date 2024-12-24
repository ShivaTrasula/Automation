package cloudFront.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cloudFont.utils.ConfigReader;
import cloudFont.utils.DriverFactory;
import cloudFront.pages.TipTopPage;

public class TipTopTests {
	private WebDriver driver;
	private TipTopPage tipTopPage;
	ConfigReader configReader;

	@BeforeClass
	public void setUp() {

		// Initialize the WebDriver and navigate to the test URL
		driver = DriverFactory.getDriver("chrome");
		configReader = new ConfigReader();
		driver.get(configReader.getUrl());
		tipTopPage = new TipTopPage(driver);
	}

	@Test(priority = 1, description = "Verify the disabled input field is actually disabled")
	public void testDisabledInput() {
		try {
			Assert.assertTrue(tipTopPage.disabledInput(), "Disabled input is not disabled");

		} catch (Exception e) {

			e.getStackTrace();
		}
	}

	@Test(priority = 2, description = "Verify the readonly input field is actually readonly")
	public void testReadonlyInput() {
		try {
			Assert.assertTrue(tipTopPage.readOnlyElement(), "Readonly input is not readonly");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3, description = "Verify the dropdown contains 8 options")
	public void testDropdownOptionsCount() {
		try {
			int expectedOptionsCount = 8;
			Assert.assertEquals(tipTopPage.getDropdownOptionsCount(), expectedOptionsCount,
					"Dropdown does not have " + expectedOptionsCount + " options");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4, description = "Verify the submit button is disabled without input data")
	public void testSubmitButtonDisabled() {
		try {
			// Assert.assertFalse(tipTopPage.clickSubmit(), "Submit button is enabled
			// without data");
			Assert.assertFalse(((WebElement) tipTopPage.submitButtonLocator).isEnabled(),
					"Submit button is enabled without data");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 5, description = "Verify the submit button is enabled after entering valid data")
	public void testSubmitButtonEnabled() {
		try {
			tipTopPage.enterName("TestUser");
			tipTopPage.enterPassword("TestPass");
			Assert.assertTrue(((WebElement) tipTopPage.submitButtonLocator).isEnabled(),
					"Submit button is not enabled after entering data");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 6, description = "Verify clicking the submit button displays the 'Received' message")
	public void testSubmitButtonFunctionality() {
		try {
			tipTopPage.clickSubmit();
			Assert.assertTrue(driver.getPageSource().contains("Received"),
					"'Received' text is not displayed after form submission");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {

		DriverFactory.quitDriver();
		openReport();

	}

	private void openReport() {
		try {
			File reportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
			if (reportFile.exists()) {
				Desktop.getDesktop().browse(reportFile.toURI());
			} else {
				System.out.println("Extent Report file not found!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

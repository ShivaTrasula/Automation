package cloudFront.reports;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import cloudFont.utils.DriverFactory;

public class ExtentTestNGListener implements ITestListener {
	private static ExtentReports extent = ExtentManager.getInstance();
	private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		testThread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testThread.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		testThread.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
		WebDriver driver = DriverFactory.getDriver();
		if (driver != null) {
			String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
			if (screenshotPath != null) {
				testThread.get().fail("Screenshot on Failure",
						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testThread.get().log(Status.SKIP, "Test Skipped: " + result.getThrowable());

	}

	@Override
	public void onStart(ITestContext context) {
		// Optional: Log framework initialization details
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private String captureScreenshot(String methodName) {
		try {
			// Define screenshot file path
			String screenshotDir = System.getProperty("user.dir") + "/screenshots/";
			Path directoryPath = Paths.get(screenshotDir);
			if (!Files.exists(directoryPath)) {
				Files.createDirectories(directoryPath);
			}

			String screenshotPath = screenshotDir + methodName + "_" + System.currentTimeMillis() + ".png";

			// Capture screenshot
			File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
			Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

			return screenshotPath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

package cloudFront.reports;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";
			ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

			try {
				sparkReporter.loadXMLConfig(
						System.getProperty("user.dir") + "/src/main/java/cloudFront/resources/extent-config.xml");
			} catch (IOException e) {

				e.printStackTrace();
			}

			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);

//	            extent.createTest("ScreenCapture")
//                .addScreenCaptureFromPath("extent.png")
//                .pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());

		}
		return extent;
	}
}

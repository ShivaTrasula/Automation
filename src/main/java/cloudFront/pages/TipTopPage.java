package cloudFront.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TipTopPage {

	WebDriver driver;

	
	private By disabledInputLocator = By.xpath("//label[normalize-space()='Disabled input']//child::input");
	private By readonlyByName = By.xpath("//input[@name='my-readonly']");
	private By readOnlyByValue = By.xpath("//input[@value='Readonly input']");
	private By dropdownColorLocator = By.xpath("//select[@name='my-select']");
	public By submitButtonLocator = By.xpath("//button[normalize-space()='Submit']");
	private By nameFieldLocator = By.xpath("//input[@name='my-name']");
	private By passwordFieldLocator = By.xpath("//input[@name='my-password']");

	public TipTopPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean disabledInput() {
		WebElement element = driver.findElement(disabledInputLocator);
		if (element.isDisplayed()) {
			System.out.println("Element is disabled");
		} else {
			System.out.println("Element is not disabled");
		}

		return !element.isEnabled();
	}

	public boolean readOnlyElement() {

		WebElement element = driver.findElement(readonlyByName);
		WebElement element1 = driver.findElement(readOnlyByValue);
		String readonlyAttribute = element.getAttribute("readonly");

		if (readonlyAttribute != null) {
			System.out.println("The input element is in readonly state.");
		} else {
			System.out.println("The input element is NOT readonly.");
		}
		return element1.getAttribute("readonly") != null;
	}

	public int getDropdownOptionsCount() {
		WebElement dropdown = driver.findElement(dropdownColorLocator);
		return dropdown.findElements(By.tagName("option")).size();
	}

	public void enterName(String name) {
		WebElement nameField = driver.findElement(nameFieldLocator);
		nameField.sendKeys(name);
	}

	public void enterPassword(String password) {
		WebElement passwordField = driver.findElement(passwordFieldLocator);
		passwordField.sendKeys(password);
	}

	public boolean isSubmitButtonEnabledWithoutData() {

		WebElement ele = driver.findElement(submitButtonLocator);

		return ele.isDisplayed();
	}

	public void clickSubmit() {
		WebElement submitButton = driver.findElement(submitButtonLocator);
		submitButton.click();
	}
}

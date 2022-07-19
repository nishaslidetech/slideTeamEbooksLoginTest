package stepDefination;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

public class GoogleLogin extends SetUPClass {

	@Given("^user is on Home Page$")
	public void user_is_on_Home_Page() throws Throwable {
		Thread.sleep(5000);
		driver.get(AppURL);
		ClearBrowserCache();
		Thread.sleep(5000);
	}

	@Then("^click on Ebook button$")
	public void click_on_Ebook_button() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("eBooks")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^click on Buynow option$")
	public void click_on_Buynow_option() throws Throwable {
		try {
			List<WebElement> buyNow = driver.findElements(By.xpath("//input[@class = 'buy-now-btn login-box']"));
			// WebElement buyNow =
			// SetUPClass.elementToBeClickable(By.xpath("//form[@id='addToCartForm-183671']//input[@name='Buy']"));
			buyNow.get(0).click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("^click on google option$")
	public void click_on_google_option() throws Throwable {
		try {
			WebElement google = SetUPClass.elementToBeClickable(By.xpath(
					"//div[@id='ajaxlogin-create-window']//a[@class='btn btn-block popup-social-btn btn-social btn-google social-btn'][normalize-space()='Sign in with Google']"));
			js.executeScript("arguments[0].click();", google);
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^enter valid credentials$")
	public void enter_valid_credentials() throws Throwable {
		try {

			if (!driver.findElements(By.xpath("//div[@class='BHzsHc']")).isEmpty()) {
				WebElement another_btn = SetUPClass
						.elementToBeClickable(By.xpath("//div[text()='Use another account']"));
				another_btn.click();
			}

			WebElement gmail_email = SetUPClass.elementToBeClickable(By.xpath("//*[@id='identifierId']"));

			gmail_email.sendKeys("parul.pahwa@slidetech.in");

			WebElement next_1 = driver.findElement(By.cssSelector("#identifierNext > div > button > span"));

			next_1.click();

			WebElement gmail_pass = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));

			gmail_pass.sendKeys("parulpahwa@12");

			WebElement next_2 = driver.findElement(By.cssSelector("#passwordNext > div > button > span"));

			next_2.click();
			Thread.sleep(6000);
			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				approve.click();
			}
		} catch (NoSuchElementException e) {

		}

	}

	@Then("^verify that user is successfully logged in$")
	public void verify_that_user_is_successfully_logged_in() throws Throwable {

	}

	@Then("^download the selected ebbok$")
	public void download_the_selected_ebbok() throws Throwable {
		Thread.sleep(2000);
		WebElement download = SetUPClass
				.elementToBeClickable(By.xpath("//button[@class = 'buy-now-btn downloadClick']"));
		download.click();
		Thread.sleep(3000);

	}

	@Then("^logout from the application$")
	public void logout_from_the_application() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			js.executeScript("arguments[0].click();", sign_Out);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("^verify that user is successfully logged out$")
	public void verify_that_user_is_successfully_logged_out() throws Throwable {

		// verify whether the account is deleted or not?
		Thread.sleep(3000);
		String verifySignOutMessage = SetUPClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));
	}

}

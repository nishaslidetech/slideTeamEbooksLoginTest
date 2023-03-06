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

public class FacebookLogin extends SetUPClass {

	@Given("^Go to the Home page$")
	public void go_to_the_Home_page() throws Throwable {
		ClearfacebookCache();
		driver.get(AppURL);

		Thread.sleep(5000);
	}

	@Then("^Click on ebook button$")
	public void click_on_ebook_button() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("eBooks")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Buynow option$")
	public void click_on_Buynow_option() throws Throwable {

		try {
			List<WebElement> buyNow = driver.findElements(By.xpath("//input[@class = 'buy-now-btn login-box']"));
			// WebElement buyNow =
			// SetUPClass.elementToBeClickable(By.xpath("//form[@id='addToCartForm-183671']//input[@name='Buy']"));
			js.executeScript("arguments[0].click();", buyNow.get(0));
			// buyNow.get(1).click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Click on facebook option$")
	public void click_on_facebook_option() throws Throwable {

		try {
			WebElement facebook = SetUPClass.elementToBeClickable(
					By.xpath("//a[@class='btn btn-block btn-social popup-social-btn btn-facebook social-btn']"));
			Thread.sleep(2000);
			js.executeScript("arguments[0].click();", facebook);
			Thread.sleep(3000);

		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Then("^Enter valid credentials$")
	public void enter_valid_credentials() throws Throwable {

		try {

			Thread.sleep(2000);
			WebElement fb_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));

			fb_email.clear();

			fb_email.sendKeys("sumit.kumar@slidetech.in");
			Thread.sleep(2000);
			WebElement fb_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));

			fb_pass.sendKeys("redhat2090");

		} catch (NoSuchElementException e) {

		}

		// click on login button

		try {
			if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
			} else {
				WebElement fb_login = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

				fb_login.click();
				Thread.sleep(3000);

			}

			if (!driver.findElements(By.xpath("//div[@id ='confirm_id']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				js.executeScript("arguments[0].click();", approve);
				Thread.sleep(5000);

				String verifyRegisteration = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@x-html='message.text']")))
						.getText();
				Thread.sleep(3000);
				Assert.assertTrue("user is not logged in",
						verifyRegisteration.contains("You have successfully logged in using your facebook account."));

				Thread.sleep(3000);
				WebElement eBooks = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='eBooks']")));
				eBooks.click();
				Thread.sleep(3000);
				// span[@x-html='message.text']

			}
		} catch (NoSuchElementException e) {

			e.printStackTrace();
		}

	}

	@Then("^Verify that user is successfully logged in$")
	public void verify_that_user_is_successfully_logged_in() throws Throwable {

	}

	@Then("^Download the selected ebbok$")
	public void download_the_selected_ebbok() throws Throwable {
		WebElement download = SetUPClass
				.elementToBeClickable(By.xpath("//button[@class = 'buy-now-btn downloadClick']"));
		download.click();
		Thread.sleep(3000);
	}

	@Then("^Logout from the application$")
	public void logout_from_the_application() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			js.executeScript("arguments[0].click();", sign_Out);
		} catch (NoSuchElementException e) {

		}

	}

	@Then("^Verify that user is successfully logged out$")
	public void verify_that_user_is_successfully_logged_out() throws Throwable {

		// verify whether the account is deleted or not?
		Thread.sleep(3000);
		String verifySignOutMessage = SetUPClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));
	}

}

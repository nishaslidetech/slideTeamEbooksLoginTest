package stepDefination;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import BaseClass.SetUPClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.config.WebDriverManagerException;

public class StripeCheckout extends SetUPClass {

	@Given("^Go to the team Home page$")
	public void go_to_the_team_Home_page() throws Throwable {
		Thread.sleep(5000);
		driver.get(AppURL);
		ClearBrowserCache();
	}

	@Then("^click on signup button$")
	public void click_on_signup_button() throws Throwable {
		Thread.sleep(4000);
		try {
			WebElement Sign_Up = driver.findElement(By.cssSelector("ul.header > li:nth-child(1) > a:nth-child(1)"));
			Thread.sleep(1000);
			Sign_Up.click();
			log.info("It's opening the website URL and redirect user to sign up page");
		} catch (NoSuchElementException popup) {
		}
	}

	@Then("^User creates a new account$")
	public void User_creates_a_new_account() throws Throwable {
		Thread.sleep(5000);
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();

		System.out.println(generatedString);

		String full_email = "selenium.testing." + generatedString + "@gmail.com";
		System.out.println(full_email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(1000);
		WebElement new_email_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email_address']")));

		new_email_signup.sendKeys(full_email);
		Thread.sleep(1000);

		// enter name
		WebElement new_fname_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("firstname")));
		new_fname_signup.sendKeys("Selenium");
		Thread.sleep(1000);

		WebElement new_lname_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastname")));
		new_lname_signup.sendKeys("Testing");
		Thread.sleep(1000);

		// enter password
		WebElement new_pwd_signup = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		new_pwd_signup.sendKeys("selenium@123");

		// verify the password
		WebElement new_pwd1_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("password-confirmation")));
		new_pwd1_signup.sendKeys("selenium@123");

		// enter captcha
		WebElement new_captcha_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("captcha_user_create")));
		new_captcha_signup.sendKeys("Aj7W2mtf9namwf55");

		// sign up button
		WebElement new_btn_signup = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".submit")));

		new_btn_signup.click();
		Thread.sleep(1000);
	}

	@Then("^verify that user is successfully signup$")
	public void verify_that_user_is_successfully_signup() throws Throwable {

	}

	@Then("^click on ebooks button$")
	public void click_on_ebooks_button() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("eBooks")));
			eBooks.click();
			Thread.sleep(4000);

			// click on Buy now btton

			WebElement buyNow = SetUPClass.elementToBeClickable(By.xpath("//button[@class = 'buy-now-btn']"));
			js.executeScript("arguments[0].scrollIntoView();", buyNow);
			js.executeScript("arguments[0].click();", buyNow);
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^verify the checkout process via stripe$")
	public void verify_the_checkout_process_via_stripe() throws Throwable {
		try {
			Thread.sleep(1000);
			WebElement option1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='amasty_stripe']")));
			option1.click();

			// click on placeorder button
			WebElement place_order_btn = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//button[@id='place-order-trigger']//span[contains(text(),'Place Order')] ")));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();", place_order_btn);
			place_order_btn.click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Then("^delete the account$")
	public void delete_the_account() throws Throwable {
		Thread.sleep(2000);
		WebElement My_Account = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My Account")));
		js.executeScript("arguments[0].click();", My_Account);

		WebElement Delete_Account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Delete Account']")));
		Thread.sleep(2000);
		Delete_Account.click();
		Thread.sleep(1000);
		WebElement radio_button = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='option1']")));
		radio_button.click();
		Thread.sleep(1000);
		WebElement delete_Profile = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Delete Profile']")));
		js.executeScript("arguments[0].scrollIntoView();", delete_Profile);
		delete_Profile.click();
		Thread.sleep(1000);
		WebElement continue_delete = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'No, delete my')]")));
		js.executeScript("arguments[0].scrollIntoView();", continue_delete);
		continue_delete.click();
		Thread.sleep(2000);

	}

	@Then("^verify the account is successfully deleted$")
	public void verify_the_account_is_successfully_deleted() throws Throwable {

		// verify that wether the account is deleted or not?
		String verifyDeleteAccount = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']"))).getText();
		Thread.sleep(3000);
		Assert.assertTrue("Account is not deleted",
				verifyDeleteAccount.contains("Your account has been deleted successfully."));
		System.out.println("your account delete successfully");
		driver.navigate().refresh();
	}

}

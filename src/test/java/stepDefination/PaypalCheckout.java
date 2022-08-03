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

public class PaypalCheckout extends SetUPClass {

	@Given("^Go to the team Home pagei$")
	public void go_to_the_team_Home_pagei() throws Throwable {

		Thread.sleep(5000);
		driver.get(AppURL);
		ClearBrowserCache();

	}

	@Then("^click on signup buttoni$")
	public void click_on_signup_buttoni() throws Throwable {

		Thread.sleep(5000);
		try {

			WebElement Sign_Up = driver.findElement(By.cssSelector("ul.header > li:nth-child(1) > a:nth-child(1)"));
			Thread.sleep(1000);
			Sign_Up.click();
			log.info("It's opening the website URL and redirect user to sign up page");
		} catch (NoSuchElementException popup) {
		}

	}

	@Then("^user creates a new account$")
	public void user_creates_a_new_account() throws Throwable {

		Thread.sleep(5000);

		// create new email for sign up

		int leftLimit = 97;
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

		String signup_email = generatedString;
		String full_email = "selenium.testing." + generatedString + "@gmail.com";
		System.out.println(full_email);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// driver.findElement(By.id("email_address")).sendKeys(full_email);

		Thread.sleep(2000);
		WebElement new_email_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email_address']")));
		Thread.sleep(2000);
		new_email_signup.sendKeys(full_email);
		Thread.sleep(2000);

		// enter name

		WebElement new_fname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstname']")));
		Thread.sleep(2000);
		new_fname_signup.sendKeys("Selenium");
		Thread.sleep(2000);

		WebElement new_lname_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='lastname']")));
		Thread.sleep(2000);
		new_lname_signup.sendKeys("Testing");
		Thread.sleep(2000);

		// enter password

		WebElement new_pwd_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password']")));
		Thread.sleep(2000);
		new_pwd_signup.sendKeys("selenium@123");
		Thread.sleep(2000);

		WebElement new_pwd1_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='password-confirmation']")));
		Thread.sleep(2000);
		new_pwd1_signup.sendKeys("selenium@123");
		Thread.sleep(2000);

		/*
		 * // enter captcha WebElement new_captcha_signup = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.id("captcha_user_create")))
		 * ; Thread.sleep(2000); new_captcha_signup.sendKeys("Aj7W2mtf9namwf55");
		 * Thread.sleep(2000);
		 */

		// sign up button

		WebElement new_btn_signup = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Sign Up']")));
		Thread.sleep(2000);
		new_btn_signup.click();
		Thread.sleep(5000);

	}

	@Then("^verify that user is successfully signupi$")
	public void verify_that_user_is_successfully_signupi() throws Throwable {

	}

	@Then("^click on ebooks buttoni$")
	public void click_on_ebooks_buttoni() throws Throwable {

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

	@Then("^verify the checkout process via paypal$")
	public void verify_the_checkout_process_via_paypal() throws Throwable {

		// select Paypal option

		try {
			boolean cp_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='paypal_express']")))
					.isSelected();
			if (cp_btn == true) {
				WebElement place_order_btn = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//button[@id='place-order-trigger']//span[contains(text(),'Place Order')] ")));

				js.executeScript("arguments[0].scrollIntoView();", place_order_btn);

				place_order_btn.click();

			} else {
				WebElement paypalOPtion = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='paypal_express']")));

				paypalOPtion.click();

				Thread.sleep(3000);

				WebElement place_order_btn = wait.until(ExpectedConditions.elementToBeClickable(
						By.xpath("//button[@id='place-order-trigger']//span[contains(text(),'Place Order')] ")));

				js.executeScript("arguments[0].scrollIntoView();", place_order_btn);

				place_order_btn.click();

			}
		} catch (Exception e) { // TODO: handle exception }
		}

	}

	@Then("^paypal popup appears and user navigates back to my account page$")
	public void paypal_popup_appears_and_user_navigates_back_to_my_account_page() throws Throwable {

		// handling window
		// Store the CurrentWindow for future reference

		String currentWindow = driver.getWindowHandle();
		String popupWindowHandle = null;

		Thread.sleep(2000);
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(currentWindow)) {

				popupWindowHandle = handle;

				Thread.sleep(1000);
				driver.switchTo().window(popupWindowHandle);

				System.out.println("Title = " + driver.getTitle());

				Assert.assertTrue("title does not matched",
						driver.getTitle().contains("Log in to your PayPal account"));
				Thread.sleep(1000);

				WebElement email = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
				email.clear();
				email.sendKeys("nisha.dhiman@slidetech.in");

				Thread.sleep(1000);
				WebElement next = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnNext']")));
				next.click();

			}
		} // This is to switch to the main window
		driver.switchTo().window(currentWindow);

	}

	@Then("^delete the accounti$")
	public void delete_the_accounti() throws Throwable {

		Thread.sleep(2000);
		WebElement My_Account = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("My Account")));
		js.executeScript("arguments[0].click();", My_Account);

		Thread.sleep(3000);
		WebElement Delete_Account = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Delete Account']")));

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

	@Then("^verify the account is successfully deletedi$")
	public void verify_the_account_is_successfully_deletedi() throws Throwable {
		// verify that wether the account is deleted or not?

		String verifyDeleteAccount = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@x-html='message.text']"))).getText();
		Thread.sleep(3000);
		Assert.assertTrue("Account is not deleted",
				verifyDeleteAccount.contains("Your account has been deleted successfully."));
		System.out.println("your account delete successfully");
		driver.navigate().refresh();

	}

}

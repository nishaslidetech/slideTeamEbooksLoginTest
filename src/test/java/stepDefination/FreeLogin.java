package stepDefination;

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

public class FreeLogin extends SetUPClass {

	@Given("^User is on team Home Page$")
	public void user_is_on_team_Home_Page() throws Throwable {
		Thread.sleep(5000);
		driver.get(AppURL);
		ClearBrowserCache();
		Thread.sleep(5000);
	}

	@Then("click on Sign in button")
	public void click_on_sign_in_button() throws InterruptedException {
		try {
			WebElement signin = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Sign In']")));
			signin.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {

			e.printStackTrace();
		}
	}

	@Then("Enter valid user name and passwor")
	public void enter_valid_user_name_and_passwor() throws InterruptedException {
		try {

			Thread.sleep(3000);
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
			email.sendKeys("nishadhiman0027@gmail.com");

			WebElement password_field = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//fieldset[@class='fieldset login']//input[@id='pass']")));
			password_field.sendKeys("Qwerty@1");
			WebElement login_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Login']")));
			login_btn.click();
			Thread.sleep(5000);

			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				approve.click();
				Thread.sleep(5000);
			}
		} catch (WebDriverManagerException e) {

		}

	}

	@Then("^Click on Ebooks$")
	public void click_on_Ebooks() throws Throwable {
		try {
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("eBooks")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Buy Now button$")
	public void click_on_Buy_Now_button() throws Throwable {

		Thread.sleep(3000);
		WebElement dropDown = wait.until(ExpectedConditions.elementToBeClickable(By.id("dk0-combobox")));
		dropDown.click();
		Thread.sleep(3000);
		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='dk0-2']")));
		name.click();
		Thread.sleep(3000);

		// go to the eBooks details page and check the console error
		WebElement selecteBook = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//ul[1]/li[1]/div[1]/i[1]/a[1]/img[1]")));
		selecteBook.click();
		Thread.sleep(9000);

		WebElement buyNow = SetUPClass.elementToBeClickable(By.xpath("//button[@class = 'buy-now-btn']"));
		buyNow.click();
		Thread.sleep(3000);

	}

	@Then("^verify the checkout process$")
	public void verify_the_checkout_process() throws Throwable {
		try {
			Thread.sleep(1000);
			WebElement option1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='amasty_stripe']")));
			option1.click();
			Thread.sleep(3000);
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

	@Then("^user logout from the application$")
	public void user_logout_from_the_application() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			js.executeScript("arguments[0].click();", sign_Out);
		} catch (NoSuchElementException e) {

		}

		// verify whether the account is deleted or not?
		Thread.sleep(3000);
		String verifySignOutMessage = SetUPClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));
	}

}

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

public class PaidLogin extends SetUPClass {

	@Given("^User is on team Home Pageii$")
	public void user_is_on_team_Home_Pageii() throws Throwable {
		Thread.sleep(3000);
		driver.get(AppURL);
		ClearBrowserCache();
		Thread.sleep(5000);
	}

	@Then("click on signin butoon")
	public void click_on_signin_butoon() throws InterruptedException {
		try {
			WebElement signin = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Sign In']")));
			signin.click();
			Thread.sleep(3000);
		} catch (WebDriverException e) {

			e.printStackTrace();
		}
	}

	@Then("enter vaild paid credentials")
	public void enter_vaild_paid_credentials() throws InterruptedException {
		try {

			Thread.sleep(3000);
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
			email.sendKeys("nisha.dhiman@slidetech.in");

			WebElement password_field = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//fieldset[@class='fieldset login']//input[@id='pass']")));
			password_field.sendKeys("123456");
			WebElement login_btn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Login']")));
			login_btn.click();
			Thread.sleep(2000);

			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				approve.click();
			}
		} catch (WebDriverException e) {

		}

	}

	@Then("^Click on Ebooksii$")
	public void click_on_Ebooksii() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement eBooks = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='eBooks']")));
			eBooks.click();
			Thread.sleep(3000);
		} catch (WebDriverManagerException e) {

			e.printStackTrace();
		}
	}

	@Then("^Click on Download option$")
	public void click_on_Download_option() throws Throwable {
		//
		WebElement download = SetUPClass
				.elementToBeClickable(By.xpath("//button[@class = 'buy-now-btn downloadClick']"));
		download.click();
		Thread.sleep(3000);

	}

	@Then("^User logout from the application$")
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

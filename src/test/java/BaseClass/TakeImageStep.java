package BaseClass;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TakeImageStep extends SetUPClass {
	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			log.info("Scenario failed, now taking screenshot");
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png" , "my screenshot");
			
		}
	}
}

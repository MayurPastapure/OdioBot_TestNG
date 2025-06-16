package utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

	public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ss = (TakesScreenshot) driver;
            File source = ss.getScreenshotAs(OutputType.FILE);
            String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timeStamp + ".png";
            File destination = new File("./screenshots/" + screenshotName);
            FileHandler.copy(source, destination);
            System.out.println("Screenshot taken: " + screenshotName);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }

}

package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClase {

	public WebDriver driver;
	public Logger logger;
	public Properties p;
	public WebDriverWait wait;

	@BeforeClass
	public void browserLounching() throws IOException {
		FileReader file = new FileReader("./src//main//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		String browser = p.getProperty("browser");

		logger = LogManager.getLogger(this.getClass());

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("Invalid browser name...");
			return;
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("qaURL"));
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}

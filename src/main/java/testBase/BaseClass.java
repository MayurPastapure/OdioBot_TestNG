package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties p;
	public static Logger logger;
	public static SoftAssert softAssert;

	@BeforeSuite
	public void browserLounching() throws IOException {
		FileReader file = new FileReader("./src//main//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		String downloadFilePath = System.getProperty("user.dir") + "\\downloadFiles";
		
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.prompt_for_download", false);
		chromePrefs.put("download.default_directory", downloadFilePath);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);

		logger = LogManager.getLogger(BaseClass.class);

		String browser = p.getProperty("browser");

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(options);
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(p.getProperty("qaURL"));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		softAssert = new SoftAssert();

	}

//	@AfterSuite
//	public void tearDown() {
//		if (driver != null) {
//			driver.quit();
//		}
//	}
}

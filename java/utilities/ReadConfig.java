package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ReadConfig {
	WebDriver driver;
	
	Properties prop;
	String path = "config.properties";

	public ReadConfig(WebDriver driver) throws IOException {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		prop = new Properties();
		FileInputStream file = new FileInputStream(path);
		prop.load(file);

	}

	public String getBrowser() {
		String browser = prop.getProperty("browser");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("Browser is not specified in config file");

	}

}

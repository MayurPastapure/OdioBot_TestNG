package stepDefination;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObject.DashboardPage;
import pageObject.KnowledgeBasePage;
import pageObject.LoginPage;
import utilities.ReadConfig;


public class BaseClass {
	
	public WebDriver driver;
	public LoginPage loginPg;
	public KnowledgeBasePage knowBasePg;
	public Logger log;
	public ReadConfig readConfig;
	public DashboardPage dashPage;
	
	
	
	
	


}

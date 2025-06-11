package OdioBot_LoginTC;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import OdioBot_LoginTC.LoginTest;
import BaseLibrary.BaseLibrary;

public class LoginTest extends BaseLibrary {
	
	LoginPage obj1;
	@BeforeTest
	public void getLaunchUrl()
	{
		getLaunchUrl("https://bot.odioiq.com/app/general/dashboard");
		obj1 = new LoginPage();
	}
	@Test(priority = 1)
	public void getTitle_odio()
	{
		obj1.getTitle();
	}
	@Test(priority = 2)
	public void getuserpass_odio() throws InterruptedException
	{
		obj1.getuserpass();
		Thread.sleep(3000);	
	}
	@Test(priority = 3)
	public void gethoversidebar() throws InterruptedException
	{
		obj1.hoversidebar();
		Thread.sleep(3000);
	}
	@Test(priority = 4)
	public void wfclick()
	{
		obj1.getwfclick();
	}
//	@Test(priority = 5)
//	public void addwfbtn()
//	{
//		obj1.getaddwfbtn();
//	}
}
package TestCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.Base;
import HomePage.homePage;

public class testCases extends Base{
	Base b=new Base();
	homePage hp=new homePage();

	@BeforeTest
	public void invokeBrowser() throws InterruptedException, IOException {
		ExtentTest logger=report.createTest("Launch  browser");
		b.InvokeBrowser();
		logger.log(Status.PASS, "Browser invoked successfully");
	}
	@Test(priority=1)
	public void homepage() throws InterruptedException, IOException {		
		hp.home();
	}
	@Test(priority=2)
	public void firstLetterApps() throws IOException {
		try {
			homePage.firstLetter();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Test(priority=3)
	public void lastLetterApps() throws IOException, InterruptedException {
		homePage.lastLetter();

	}
	@AfterTest
	public void closeBrowser() {
		ExtentTest logger=report.createTest("Close Browser");		
		logger.log(Status.PASS, "Browser Closed Successfully");
		b.closebrowser();
	}
}


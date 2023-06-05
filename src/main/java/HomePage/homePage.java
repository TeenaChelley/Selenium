package HomePage;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.Base;
import utils.ExtentReportManager;

public class homePage extends Base{
	static ExtentReports report=ExtentReportManager.getReportInstance();
	
	public void home() throws InterruptedException, IOException {		
		String n=driver.findElement(name).getText();
		String d=driver.findElement(title).getText();				
		String name=(n+" - "+d);
		System.out.println("User details :  " +name+"\n");
		ExtentTest logger=report.createTest("Launch  Apps &Tools");
		driver.findElement(apps).click();
		Thread.sleep(2000);
		String path=Screenshot("Apps & Tools");
		logger.log(Status.PASS, "Apps & Tools opened Sucessfully")
		.addScreenCaptureFromPath(path,"Apps & Tools");
		driver.findElement(s).click();
		Thread.sleep(2000);
	}
	public static void firstLetter() throws InterruptedException, IOException {
		ExtentTest logger=report.createTest("apps with S - Letter");
		String path=Screenshot("S-LetterApps");
		List <WebElement>s=driver.findElements(s_apps);
		int sSize = s.size();
		System.out.println("*** Apps starts with letter - S ***");
		for(int i=0;i<sSize;i++){
			System.out.println(s.get(i).getText());
		}
		System.out.println("\n");
		logger.log(Status.PASS,"First Letter Apps and Tools are printed Successfully")
		.addScreenCaptureFromPath(path,"S - Letter apps");
	}
	public static void lastLetter() throws InterruptedException, IOException {
		ExtentTest logger=report.createTest("Apps starts with letter - I");
		
		driver.findElement(i).click();
		Thread.sleep(2000);
		String path=Screenshot("I-LetterApps");
		List <WebElement>i=driver.findElements(i_apps);
		int SSize = i.size();
		System.out.println("*** Apps starts with letter - I ***");
		for(int j=0;j<SSize;j++){
			System.out.println(i.get(j).getText());
		}
		System.out.println("\n");
		logger.log(Status.PASS,"Last Letter Apps and Tools are printed Successfully")
		.addScreenCaptureFromPath(path,"I letter apps");
	}
	
}


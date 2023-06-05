package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReportManager;
public class Base {
	public static WebDriver driver;
	public static By backbtn=By.id("idBtn_Back");
	public static By name=By.id("user-name");
	public static By username=By.id("i0116");
	public static By password=By.id("i0118");
	public static By title=By.className("job-title");
	public static By apps=By.xpath("//div[@class='slick-slide slick-current slick-active']");
	public static By s=By.xpath("//button[normalize-space()='S']");
	public static By i=By.xpath("//button[normalize-space()='I']");
	public static By s_apps=By.xpath("//span[@class='apps-and-tools__card__title ellipsis ng-binding']");
	public static By i_apps=By.xpath("//span[@class='apps-and-tools__card__title ellipsis ng-binding']");
	public static Properties prop;

	protected ExtentReports report=ExtentReportManager.getReportInstance();
	public void InvokeBrowser() throws InterruptedException, IOException {
		prop =new Properties();
		
		try {
            prop.load(new FileInputStream("src/main/java/Config/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Username :");
		String user=sc.next();
		System.out.println("Enter Your Password :");
		String pass=sc.next();
		if(prop.getProperty("browserName").matches("1")) {
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			


			driver.get(prop.getProperty("websiteURLKey"));
			Thread.sleep(5000);
			
			driver.findElement(username).sendKeys(user,Keys.ENTER);
			Thread.sleep(2000);
			driver.findElement(password).sendKeys(pass,Keys.ENTER);
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlToBe("https://login.microsoftonline.com/common/SAS/ProcessAuth"));
			driver.findElement(backbtn).click();
			Thread.sleep(2000);
			ExtentTest logger=report.createTest("HomePage");
			String path=Screenshot("HomePage");
			logger.log(Status.PASS, "HomePage Opened")
			.addScreenCaptureFromPath(path,"HomePage");
		}
		else if(prop.getProperty("browserName").matches("2")) {
			
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			
			driver.get(prop.getProperty("websiteURLKey"));
			Thread.sleep(5000);
			
//			driver.findElement(username).sendKeys(user,Keys.ENTER);
//			Thread.sleep(2000);
//			driver.findElement(password).sendKeys(pass,Keys.ENTER);
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.urlToBe("https://be.cognizant.com/"));

			//driver.findElement(backbtn).click();

			String path=Screenshot("HomePage");
			ExtentTest logger=report.createTest("HomePage");
			logger.log(Status.PASS, "HomePage Opened")
			.addScreenCaptureFromPath(path,"HomePage");
		}

	}

	public static String Screenshot(String fileName) throws IOException {
		TakesScreenshot s=(TakesScreenshot)driver;
		File src=s.getScreenshotAs(OutputType.FILE);
		File dst=new File("./screenshots/"+fileName+".png");
		FileUtils.copyFile(src, dst);
		
		return dst.getAbsolutePath();
	}

	public void closebrowser() {
		report.flush();
		driver.quit();
		
	}

}

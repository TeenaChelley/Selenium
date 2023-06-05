package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

 

public class ExtentReportManager {
    public static ExtentReports report;
   public static ExtentReports getReportInstance(){

        if(report== null){
            ExtentSparkReporter htmlReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Report/extentreport.html");
            report=new ExtentReports();
            report.attachReporter(htmlReporter);
        }

        return report;
    }

 

}
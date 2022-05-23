package utility;


	import java.io.IOException;
import java.text.SimpleDateFormat;
	import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;





	public class ExtentReport {
		public ExtentReports extent;
		public ExtentTest test;
		public String timeStamp;
		public String snapshotPath ;
		public Snapshot snap;
		
		@BeforeTest
		public void createReport() {
			timeStamp="./reports";
			Date d=new Date();
			String date=new SimpleDateFormat("MM-dd-yy-HH-mm-ss").format(d);
			extent=new ExtentReports(System.getProperty("user.dir")+"./reports/rp"+date+".html",true);
			extent.addSystemInfo("Host Name", "windows");
			extent.addSystemInfo("User Name", "anuja ");
			extent.addSystemInfo("Environment", "QA");
				
			}
			
			
			
		
		@BeforeMethod
		public ExtentTest createTest(String Testname) {
			test=extent.startTest(Testname);
			test.assignAuthor("anuja shelke");
			return test;
		
		}
		public void logPass( String text) {
			test.log(LogStatus.PASS,text);
			test.log(LogStatus.PASS,test.addBase64ScreenShot(text));
		}
		public void logFail(String text) {
			test.log(LogStatus.FAIL, text);
			test.log(LogStatus.FAIL,test.addBase64ScreenShot(text));
			
		}
		
		@AfterTest
		public void endReport(){
			extent.endTest(test);
			extent.flush();
			extent.close();
		}




		public void logSkip(String string) {
			test.log(LogStatus.SKIP,string);
			
		}
	  
	

	}


package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestNGListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestNGListener{

	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context) {
		
		String timestamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time Stamp
		repName="Test-Report-"+timestamp+".html";
		
		sparkreporter=new ExtentSparkReporter(".\\reports\\"+repName); // specify location of the report
		
		sparkreporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of report
		
		sparkreporter.config().setReportName("Pet Stores Users API");// name of the report
		
		sparkreporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Application", "Pet Stores Users API");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User", "Karthick");
		
		
	}

	public void OnTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");
		
	}

	public void OnTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	public void OnTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void OnFinish(ITestContext context) {
		// TODO Auto-generated method stub
	    extent.flush();
	}
	
	
}

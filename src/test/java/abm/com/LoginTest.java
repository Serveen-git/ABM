package abm.com;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	
	WebDriver driver;
	ExtentSparkReporter spark = new ExtentSparkReporter("htmlReport.html");
	ExtentReports report = new ExtentReports();
	
	@BeforeTest 
	private void setup() {
		report.attachReporter(spark);
		WebDriverManager.chromedriver();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void ABM_REQ_01_TC_01() throws IOException {
		ExtentTest test = report.createTest("TESTCASE : ABM_REQ_01_TC_01").assignAuthor("Test Engineer 1")
				.assignCategory("Functional").assignDevice("Windows");
		test.info("Verifying page title");
		try {
			driver.get("http://localhost/abm/index.html");
			String title = driver.getTitle();
			if(title.equals("Software Quality Assurance Group")) {
				test.pass("Page title mateched");
				driver.findElement(By.xpath("//*[@id=\"dropdownMenuButton2\"]")).click();
				driver.findElement(By.xpath("//*[@id=\"templatemo_main_nav\"]/div[2]/ul/li[2]/a")).click();
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				test.pass("Navigated into login page");
			}else {
				test.fail("Page title is not matched");
			}
		} catch (Exception e) {
			test.fail("Unexpected error :" +e.getMessage());
			test.addScreenCaptureFromPath(CaptureScreenshort.TakeScreenshort(driver));
		}
	}
	
	@Test(priority = 2)
	public void ABM_REQ_01_TC_02() throws IOException {
		ExtentTest test = report.createTest("TESTCASE  : ABM_REQ_01_TC_02").assignAuthor("Test Engineer 1")
				.assignCategory("Functional").assignDevice("Windows");
		test.info("Verifying valid username and password");
		try {
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[2]/input")).sendKeys("RC202");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[3]/input")).sendKeys("RC202@123");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[6]/div/input")).click();
			test.pass("Username and password is verified sucessfully");
		} catch (Exception e) {
			test.fail("Unexpected error :" +e.getMessage());
			test.addScreenCaptureFromPath(CaptureScreenshort.TakeScreenshort(driver));
		}
	}
	
	@Test (priority = 3, dependsOnMethods = "ABM_REQ_01_TC_02")
	public void ABM_REQ_01_TC_03() throws IOException {
		ExtentTest test = report.createTest("TESTCASE  : ABM_REQ_01_TC_03").assignAuthor("Test Engineer 1")
				.assignCategory("Functional").assignDevice("Windows");
		test.info("Verifying logout");
		try {
			driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li/a/i")).click();
			driver.findElement(By.xpath("/html/body/div/header/nav/div/ul/li/ul/li/div/a")).click();
			test.pass("Logged out sucessfully");
		} catch (Exception e) {
			test.fail("Unexpected error :" +e.getMessage());
			test.addScreenCaptureFromPath(CaptureScreenshort.TakeScreenshort(driver));
		}
	}
	
	@Test(priority = 4)
	public void ABM_REQ_01_TC_04() throws IOException {
		ExtentTest test = report.createTest("TESTCASE  : ABM_REQ_01_TC_04").assignAuthor("Test Engineer 2")
				.assignCategory("Functional").assignDevice("Windows");
		test.info("Verifying invalid username");
		try {
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[2]/input")).sendKeys("RC222");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[3]/input")).sendKeys("RC202@123");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[6]/div/input")).click();
			test.pass("Invalid username verified sucessfully");
		} catch (Exception e) {
			test.fail("Unexpected error :" +e.getMessage());
			test.addScreenCaptureFromPath(CaptureScreenshort.TakeScreenshort(driver));
		}
	}
	@Test(priority = 5)
	public void ABM_REQ_01_TC_05() throws IOException {
		ExtentTest test = report.createTest("TESTCASE  : ABM_REQ_01_TC_05").assignAuthor("Test Engineer 2")
				.assignCategory("Functional").assignDevice("Windows");
		test.info("Verifying invalid password");
		try {
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[2]/input")).sendKeys("RC202");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[3]/input")).sendKeys("RC201@123");
			driver.findElement(By.xpath("//*[@id=\"home\"]/form/div/div/div/div[6]/div/input")).click();
			test.pass("Invalid password verified sucessfully");
		} catch (Exception e) {
			test.fail("Unexpected error :" +e.getMessage());
			test.addScreenCaptureFromPath(CaptureScreenshort.TakeScreenshort(driver));
		}
	}
	
	@AfterTest
	public void teardown() {
		report.flush();
		driver.quit();
	}
}

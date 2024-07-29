package abm.com;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshort {
	public static String TakeScreenshort(WebDriver driver) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File("src/../images/screenshort"+System.currentTimeMillis()+".png");
		FileUtils.copyFile(src, dest);
		String abs = dest.getAbsolutePath();
		return abs;
	}
}

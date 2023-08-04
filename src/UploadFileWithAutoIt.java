import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFileWithAutoIt {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		
		String downloadPath = System.getProperty("user.dir");
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		ChromeDriver driver = new ChromeDriver(options);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://smallpdf.com/pdf-to-word");
		driver.findElement(By.cssSelector(".sc-8s01yt-5.gGeCVP")).click();
		
		Runtime.getRuntime().exec("C:\\Users\\soumy\\OneDrive\\Documents\\Selenium\\AutoIt commands\\First Command.exe");
		//Thread.sleep(8000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Continue')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Continue')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Download')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Download')]")).click();
		Thread.sleep(5000);
		File f = new File(downloadPath+"/Doc.docx");
		if(f.exists())
		{
			Assert.assertTrue(f.exists());
			
			if(f.delete())
			{
				System.out.println("File Deleted");
			}
				
			
		}
		
		

	}

}

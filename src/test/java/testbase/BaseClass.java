package testbase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public Logger logger; //For Logging -- Log4j
	public ResourceBundle rb; // Properties

	@BeforeClass(groups = { "regression", "master", "sanity"})
	@Parameters({ "browser" })
	public void setup(String br) {
		
		//Loading properties file
		rb = ResourceBundle.getBundle("config");

		// Logging
		logger = LogManager.getLogger(this.getClass());

		// Drivers
		if (br.equals("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Launched Chrome Browser");
		} 
		else if (br.equals("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("Launched Edge Browser");
		}
		/*
		 * else if(br.equals("firefox")) { WebDriverManager.firefoxdriver().setup();
		 * driver=new FirefoxDriver(); logger.info("Launched Firefox Browser"); }
		 */
		else 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Launched Chrome Browser");
		}
		logger.info("Launching application.");
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups = { "regression", "master", "sanity"})
	public void tearDown() {
		driver.quit();
	}

	public String randomstring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}

	public int randomNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(5);
		return (Integer.parseInt(generatedString2));
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
	}
}

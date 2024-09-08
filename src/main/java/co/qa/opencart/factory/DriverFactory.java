package co.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;

	public static String highlight;

	public WebDriver initDriver(Properties prop) {

		String BrowserName = prop.getProperty("browser");

		System.out.println("Browser Name :" + BrowserName);

		highlight = prop.getProperty("highlight");

		if (BrowserName.trim().equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BrowserName.trim().equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (BrowserName.trim().equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			System.out.println("Please pass the right browser" + BrowserName);
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");

		return driver;
	}

	public Properties initProp() {

		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

}

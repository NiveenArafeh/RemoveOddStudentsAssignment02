package com.studenets;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class RemoveOdd {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, IOException {

		driver = runBrowser("edge", driver);

		driver.manage().window().maximize();
		driver.get("http://127.0.0.1:5500/index.html");

		List<WebElement> allStudents = driver.findElements(By.tagName("option"));
		int totalSizeBeforeRemoving = allStudents.size(); // 16 students

		System.out.println("All stusdents before removing : " + allStudents.size());

		int count = 0;
		
		for (int i = 0; i < totalSizeBeforeRemoving; i++) {

			if (i % 2 != 0) // if odd number ,remove the student
			{
				System.out.println(
						"the removed person  is " + driver.findElements(By.tagName("option")).get(i).getText());
				driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();
				Thread.sleep(2000);
				count++;
				totalSizeBeforeRemoving = totalSizeBeforeRemoving - 1;
				takeScreenShot();

			}//end if

		}//end of for loop
		System.out.println("The number of removed persons:" + count);
		List <WebElement> studentsAfterRemoving= driver.findElements(By.tagName("option"));
		System.out.println("Total size after removing:" +studentsAfterRemoving.size() );

	}

	public static void takeScreenShot() throws IOException {
		Date currenTime = new Date();
		String screenshotFileName = currenTime.toString().replace(":", "-");
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("./screenshot/" + screenshotFileName + ".png");
		FileUtils.copyFile(SrcFile, DestFile);
	}

	public static WebDriver runBrowser(String browser, WebDriver driver) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");

			FirefoxOptions options = new FirefoxOptions();
			options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

			driver = new FirefoxDriver(options);

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		return driver;

	}

}

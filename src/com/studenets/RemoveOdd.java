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

	public static void main(String[] args) throws  IOException {

		driver = runBrowser("edge", driver);

		driver.manage().window().maximize();
		driver.get("http://127.0.0.1:5500/index.html");

		List<WebElement> allStudents = driver.findElements(By.tagName("option"));
		int totalSizeBeforeRemoving = allStudents.size(); // 16 students

		System.out.println("All stusdents before removing : " + allStudents.size());
		List <WebElement> studentsAfterRemoving;

	
		
		for (int i = 0; i < allStudents.size(); i++) {
			
			
			if (i%2!=0) // if index[1]  ,remove the student
			{
				allStudents.get(i).click();
				System.err.println(
						"the removed person  is " + allStudents.get(i).getText());
				driver.findElement(By.xpath("//*[@id=\"remove\"]")).click();
			
				//takeScreenShot();
         
			}
			else {
				System.out.println("We will not remove "+ allStudents.get(i).getText());
			}//end if
		
			//studentsAfterRemoving=driver.findElements(By.tagName("option"));
			//totalSizeBeforeRemoving=studentsAfterRemoving.size();
			
		
		}//end of for loop
		/*List<WebElement> myList = driver.findElements(By.tagName("option"));

		for (int i = 0 ; i < myList.size() ; i ++) {
			

			if (i%2!=0) {
				myList.get(i).click();
				System.err.println(myList.get(i).getText()+" sorry bye bye");

				driver.findElement(By.id("remove")).click();
				Thread.sleep(3000);

			}
			else {
				System.out.println("i will keep "+myList.get(i).getText());
			}
		}*/

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

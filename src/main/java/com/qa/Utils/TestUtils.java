package com.qa.Utils;

import com.qa.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;

import javax.xml.parsers.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestUtils {
	private static final Logger log = LogManager.getLogger(TestUtils.class);
		public void log(String txt) {

		BaseTest base = new BaseTest();
		String msg = Thread.currentThread().getId() + ":"
				+ Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
		System.out.println(msg);
		String strFile = "logs" + File.separator + File.separator + base.getDateTime();

		File logFile = new File(strFile);

		if (!logFile.exists()) {
			logFile.mkdirs();
		}

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + File.separator + "log.txt",true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(msg);
	    printWriter.close();
	}

	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}
	public void waitFor(int seconds) {
		try{
			Thread.sleep(seconds);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void waitUntilElementInVisible(WebElement ele, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(ele));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
	public Set<String> fetchAllContext(AppiumDriver<WebElement> driver)
	{
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			System.out.println("Fetching: "+context);
		}
		return contexts;
	}
	public void switchToNativeContext(AppiumDriver<WebElement> driver)
	{
				Set<String> contexts=fetchAllContext(driver);
		if((driver.getContext()).equalsIgnoreCase("NATIVE_APP"))
		{
			log("Already in NATIVE_APP context");
		}
		else {
			for (String context : contexts) {
			log(context);
			if ((context.equalsIgnoreCase("NATIVE_APP")))
			{
				driver.context(context);
				break;
			}
				}
			}
	}



	public void switchToWebViewContext(AppiumDriver<WebElement> driver) {
		Set<String> contexts = fetchAllContext(driver);
		if((driver.getContext().equalsIgnoreCase("WEBVIEW_com.dbs.in.cbcards")))
		{
			log("Already in WEBVIEW_com.dbs.in.cbcards context");

		}
		else{
			for (String context : contexts) {
			log(context);
			if (context.equalsIgnoreCase("WEBVIEW_com.dbs.in.cbcards")) {
				driver.context(context);
				log("Switching to: "+context);
				break;
			}}
		}
	}
	public void switchToChromeWebView(AppiumDriver<WebElement> driver) {
		Set<String> contexts = fetchAllContext(driver);
		if((driver.getContext().equalsIgnoreCase("WEBVIEW_chrome")))
		{
			log("Already in WEBVIEW_chrome context");
		}
		else{
		for (String context : contexts) {
			log(driver.getContext());
			 if (context.equalsIgnoreCase("WEBVIEW_chrome")) {
				driver.context(context);
				log("Switching to: "+context);
				break;
			}}
		}
	}
	public void switchtochrome(AppiumDriver<WebElement> driver){
		int timeout = 30; // Timeout in seconds
		long startTime = System.currentTimeMillis();

		while ((System.currentTimeMillis() - startTime) < (timeout * 1000)) {
			Set<String> contextHandles = driver.getContextHandles();
			for (String context : contextHandles) {
				if (context.contains("WEBVIEW_chrome")) {
					driver.context(context);
				}
			}
			try {
				TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}
	public void waitUntilElementToBeClickable(WebElement ele, WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	}

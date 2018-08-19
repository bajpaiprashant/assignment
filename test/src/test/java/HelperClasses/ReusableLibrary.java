package HelperClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogLevel;
import com.relevantcodes.extentreports.LogStatus;

import reporting.ExtentReport;


public class ReusableLibrary{
	

	public WebDriver driver;
	ExtentReports extent;
	ExtentTest logger;
	int i=0;

	
	
	/**
	 * @author prashantbajpai
	 * @param browserName
	 * @param url
	 * @description This method is to launch the app in the given browser
	 */
	public void launchApp(String browserName, String url) {
		try {
			if(browserName.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "chromedriver");
				driver=new ChromeDriver();
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				
			}
			else if(browserName.equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver","geckodriver");
				driver=new FirefoxDriver();	
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				driver.manage().window().maximize();

			}
			System.out.println("Browser loaded "+browserName);
		} catch (WebDriverException e) {
			System.out.println("Browser didn'load "+browserName);
		}
		finally{
			takeSnap();
		}

	}
	
	/**
	 * @author prashantbajpai
	 * @param url
	 * @description This method is to launch the given app
	 */
	public void launchApp(String url) {
		try {
			driver.get(url);
		} catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}

	}

	/**
	 * @author prashantbajpai
	 * @param id
	 * @param dataToEnter
	 * @description This method is to enter data into a text box
	 */
	public void enterById(String id, String dataToEnter) {
		try {
			driver.findElement(By.id(id)).clear();
			driver.findElement(By.id(id)).sendKeys(dataToEnter);
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");

		}
		catch(WebDriverException e){
			System.out.println("Browser Not found");

		}
		finally{
			//take screenshot
			takeSnap();
		}

	}

	/**
	 * @author prashantbajpai
	 * @param classname
	 * @description This method is to click an element using class name
	 */
	public void clickByClassName(String className) {
		try {
			driver.findElement(By.className(className)).click();
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch(WebDriverException e){
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}



	}

	
	/**
	 * @author prashantbajpai
	 * @description This method verifies the title of the web page
	 * @param expectedTitle
	 * @return true or false based on the title verification
	 */
	public boolean verifyTitle(String expectedTitle) {
		try {
			if(driver.getTitle().equals(expectedTitle)){
				System.out.println("Title of the page is as expected");
				return true;
			}
			else{
				System.out.println("Title of the page is not as expected");
				return false;
			}
		} 
		catch(WebDriverException e){
			
			System.out.println("Browser Not found");
			return false;
		}
		

	}

	/**
	 * 
	 * @author prashantbajpai
	 * This method closes all the open browsers
	 */
	public void closeAllBrowsers() {
		driver.quit();

	}

	/**
	 * @author prashantbajpai
	 * This methid is used to take a screen shot in the given page
	 */
	public void takeSnap() {
		
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./image/screen"+i+".jpeg"));
			i++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author prashantbajpai
	 * This method is used to switch to a browser window
	 */
	public void switchToPrimaryWindow()
	{
	try {
	Set<String> st= driver.getWindowHandles();
	for (String link:st)
	{

	driver.switchTo().window(link);
	break;
	}
	} catch (WebDriverException e) {
	System.out.println("Main Window not found");
	}
	}

	/**
	 * @author prashantbajpai
	 * This method is used to switch to last browser window
	 */
	public void switchToLastWindow()
	{
	try {
	Set<String> st= driver.getWindowHandles();
	for (String link:st)
	{

	driver.switchTo().window(link);
	}
	} catch (WebDriverException e) {
	// TODO Auto-generated catch block
	System.out.println("Main Window not found");
	}

	}

	/**
	 * @param frameelement
	 * @author prashantbajpai
	 * This method is used to switch to frame window
	 */
	public void switchToFrameByElement(WebElement frameelement ) {
		try {
			driver.switchTo().frame(frameelement);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
		takeSnap();
		}
		
	}

	/**
	 * @author prashantbajpai
	 * @param frame
	 * This method is used to switch to first frame window
	 * 
	 */
	public void switchToFirstFrame(List<WebElement> frame ) {
		try {
			driver.switchTo().frame(0);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}
	}

	/**
	 * @author prashantbajpai
	 * @param frame
	 * This method is used to switch to last frame window
	 * 
	 */
	public void switchToLastFrame(List<WebElement> frame ) {
		try {
			int n=frame.size();
			driver.switchTo().frame(n);
		}catch (NoSuchFrameException e) {
			System.out.println("Frame Not found");
		}
		
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}

	}

	/**
	 * @author prashantbajpai
	 * This methiod is used to accept a alert message
	 */
	public void acceptAlert() {
		try {
			Alert al=driver.switchTo().alert();
			al.accept();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}

	}
	
	
	/**
	 * @author prashantbajpai
	 * @param linktext
	 * This method is used to click an element using link text
	 */
	public void clickByLinkText(String linktext) {
		try {
			driver.findElement(By.linkText(linktext)).click();
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
	

	}



	/**
	 * @author prashantbajpai
	 * @param url
	 * This method is used to verify the page URL
	 */
	public void verifyCurrentUrl(String url) {
		try {
			if (url.equalsIgnoreCase(driver.getCurrentUrl())){
				System.out.println("Url is same");
			}
			else
			{
				System.out.println("Url is Not same");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}

	}

	/**
	 * @param text
	 * @param id
	 * This method is used to verify a given text in the UI by using ID as locator
	 */
	public void verifyTextById(String text,String id) {
		try {
			if (text.equalsIgnoreCase(driver.findElement(By.id(id)).getText())){

				System.out.println("Text matched");
			}
			else
			{
				System.out.println("Text did not matched,Failed");
			}
		}catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}


	}

	/**
	 * @param text
	 * @param id
	 * This method is used to verify a given text in the UI by using xpath as locator
	 */
	public void verifyTextByXpath(String text , String xpath) {
		try {
			if (text.equalsIgnoreCase(driver.findElement(By.xpath(xpath)).getText())){

				System.out.println("Text is same");
			}
			else
			{
				System.out.println("Text is Not same");
			}
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}


	}
	
	
	/**
	 * @author prashantbajpai
	 * @param name
	 * This method is used to click a name using its name as locator
	 */
	public void clickByName(String name) {
		try {
			driver.findElement(By.name(name)).click();
		}catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}

	}

	/**
	 * @author prashantbajpai
	 * @param xpath
	 * This method is used to click a name using its xpath as locator
	 */
	public void clickByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(xpath)).click();
		}catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
	
	}
	
	/**
	 * @author prashantbajpai
	 * @param id
	 * This method is used to click a name using its ID as locator
	 */
	public void clickByID(String id) {
		try {
			driver.findElement(By.id(id)).click();
		}catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
	
	}


	/**
	 * @author prashantbajpai
	 * @param name,text
	 * This method is used to enter text value into textbox
	 */
	public void enterByName(String name, String text) {
		try {
			driver.findElement(By.name(name)).sendKeys(text);
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
	/*	finally{
			takeSnap();
		}
*/
	}

	/**
	 * @author prashantbajpai
	 * @param id
	 * This method is used to click a element by xpath
	 */
	public void enterByXpath(String xpath, String text) {
		try {
			driver.findElement(By.xpath(xpath)).sendKeys(text);
		} catch (NoSuchElementException e) {
			System.out.println("Element Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		finally{
			takeSnap();
		}
	}
	/**
	 * @author prashantbajpai
	 * This method is used to dismiss a alert
	 */
	public void dismissAlert(){
		try {
			Alert al=driver.switchTo().alert();
			al.dismiss();
		} catch (NoAlertPresentException e) {
			System.out.println("Alert Not found");
		}
		catch (WebDriverException e) {
			System.out.println("Browser Not found");
		}
		

	}

	/**
	 * @author prashantbajpai
	 * This method is to verify if a given element is present
	 * 
	 */
	public boolean isElementPresent(By ele){
		try{
		if(driver.findElement(ele).isDisplayed()||driver.findElement(ele).isSelected()||driver.findElement(ele).isEnabled()){
			return true;
		}
		}
		catch(Exception ex){
			return false;
		}
		return true;
		
	}
	public void selectByValue(String ele, String text) {
		try {
			if(driver.findElements(By.id(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.id(ele)));
				sel.selectByValue(text);
			}
			else if(driver.findElements(By.className(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.className(ele)));
				sel.selectByValue(text);
			}
			else if(driver.findElements(By.name(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.name(ele)));
				sel.selectByValue(text);
			}
			else if(driver.findElements(By.xpath(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.xpath(ele)));
				sel.selectByValue(text);
			}
		} catch (NoSuchElementException e) {
			System.out.println("No Select element found");
		}
		finally{
			takeSnap();
		}
		
		
		
	}

	public void selectByIndex(String  ele, int i) {
		try {
			if(driver.findElements(By.id(ele)).size()>0){
				Select sel=new Select(driver.findElement(By.id(ele)));
				sel.selectByIndex(i);
			}
			else if(driver.findElements(By.className(ele)).size()!=0){
				Select sel=new Select(driver.findElement(By.className(ele)));
				sel.selectByIndex(i);
			}
			else if(driver.findElements(By.name(ele)).size()>0){
				Select sel=new Select(driver.findElement(By.name(ele)));
				sel.selectByIndex(i);
			}
			else if(driver.findElements(By.xpath(ele)).size()>0){
				Select sel=new Select(driver.findElement(By.xpath(ele)));
				sel.selectByIndex(i);
			}
		} catch (NoSuchElementException e) {
			System.out.println("No Select element found");
		}
		finally{
			takeSnap();
		}
	}

	public void selectByVisisbleText(String ele, String text) {
		
	    WebDriverWait noThreadSleep = new WebDriverWait(driver, 35);
	    noThreadSleep.pollingEvery(250, TimeUnit.MILLISECONDS);


		try {
			if(driver.findElements(By.id(ele)).size()!= 0){
				Select sel=new Select(driver.findElement(By.id(ele)));
				sel.selectByVisibleText(text);
			}
			else if(driver.findElements(By.className(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.className(ele)));
				sel.selectByVisibleText(text);
			}
			else if(driver.findElements(By.name(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.name(ele)));
				sel.selectByVisibleText(text);
			}
			else if(driver.findElements(By.xpath(ele)).size() != 0){
				Select sel=new Select(driver.findElement(By.xpath(ele)));
				sel.selectByVisibleText(text);
			}
		} catch (NoSuchElementException e) {
			System.out.println("No Select element found");		
			}
		finally{
			takeSnap();
		}
		
	}
	
	public String[][] getAllSheetData(String flilePath, int sheetIndex)
	{
		
		FileInputStream fis;
		XSSFWorkbook wb=null;

		String[][] sheetData= null;
			
			try {
				//Create FileInputStream object with given File path
				fis = new FileInputStream(new File(flilePath));
				//Create workbook object with FileInputStream
				 wb = new XSSFWorkbook(fis);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println("File not Found");
			}
			catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("File not handled");
			
			}
		
		
		//Referring particular sheet according to given sheet index value
		 XSSFSheet sht = wb.getSheetAt(sheetIndex);
		int rows=sht.getLastRowNum();
		int columns= sht.getRow(0).getLastCellNum();
		sheetData = new String[rows+1][columns];
		
		//Loop for moving each row
		for (int i=0;i<=rows;i++)
		{
			//System.out.println(sht.getLastRowNum());
		XSSFRow row= sht.getRow(i);
				
		//Loop for moving each column
		for (int j=0;j<row.getLastCellNum();j++)
		{
			try {
				sheetData[i][j]=row.getCell(j).getStringCellValue();
			//	System.out.println(row.getCell(j).getStringCellValue());
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				System.out.println("No Value Found");
			}
		}
		
		}
		
		return sheetData;
		
	}

	public String getvalue(String ele) {
		String eleVlaue=null;
		try {
			if(driver.findElements(By.xpath(ele)).size()!= 0){
				eleVlaue= driver.findElement(By.xpath(ele)).getText();
			}
			else if(driver.findElements(By.id(ele)).size() != 0){
				eleVlaue= driver.findElement(By.id(ele)).getText();
			}
			else if(driver.findElements(By.name(ele)).size() != 0){
				eleVlaue= driver.findElement(By.name(ele)).getText();
			} 
			else if(driver.findElements(By.className(ele)).size() != 0){
				eleVlaue= driver.findElement(By.className(ele)).getText();
			}
		} catch (NoSuchElementException e) {
			System.out.println("No element found ");	

			}
		finally{

			takeSnap();
		}
		return eleVlaue;

	}
	
	public void clickByJS(String ele){
	WebElement rateElement = driver.findElement(By.xpath(ele));
	((JavascriptExecutor)driver).executeScript("arguments[0].click();", rateElement);
	}


	




}

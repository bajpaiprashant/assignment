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

public class ReusableLibrary{
	

	public WebDriver driver;
	int i=0;

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
			takeSnap();
		}

	}

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

	public void verifyTitle(String expectedTitle) {
		try {
			if(driver.getTitle().equals(expectedTitle)){
				System.out.println("Login successfull");

			}
		} 
		catch(WebDriverException e){
			System.out.println("Browser Not found");
		}
		

	}

	public void closeAllBrowsers() {
		driver.quit();

	}

	public void takeSnap() {
		
		File src=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("./image/screen"+i+".jpeg"));
			i++;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

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
	// TODO Auto-generated catch block
	System.out.println("Main Window not found");
	}
	}

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

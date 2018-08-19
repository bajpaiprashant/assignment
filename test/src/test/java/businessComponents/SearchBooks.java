package businessComponents;


import java.io.File;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import HelperClasses.BookDeatils;
import HelperClasses.ReusableLibrary;
import reporting.ExtentReport;
import uimap.Home_PageObjects;

public class SearchBooks extends ReusableLibrary{

	
	private String isbn_num;
	private String title;
	private String author_Name;
	private Double price_Kindle_Edition;
	private Double price_paperback_Edition;
	private String publisher;
	ExtentReports extent;
	ExtentTest logger;
	BookDeatils bd;
	//ITestResult result;

	HashMap<String,BookDeatils> bookDet=new HashMap<String,BookDeatils>();
	//ExtentReport etentReport=new ExtentReport();
	//BookDeatils bookDeatils=null;
	
	
	public boolean launchBrowserAndUrl(String browser, String URL) throws InterruptedException{	
		try{
		launchApp(browser, URL);
		return true;
		}
		catch(Exception e){
				return false;
		}
	}
		
		@Test
		public void testCase1() throws InterruptedException{
			//start the logger to log into report
			logger = extent.startTest("testCase1");
			//verify if the browser is launched
			if(launchBrowserAndUrl("chrome", "https://www.amazon.com/")){
				logger.log(LogStatus.PASS, "verify browser is launched", "Browser launched successfully");				
			}
			else{
				logger.log(LogStatus.FAIL, "verify browser is launched", "Browser did not launched successfully");				
	
			}
			//verify the title of the page
			if(verifyTitle("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more")){
				logger.log(LogStatus.PASS, "Verify title of the page", "Title of the page is as expected");				

			}
			else{
				logger.log(LogStatus.FAIL, "Verify title of the page", "Title of the page is not as expected");				

			}
			
			//verify the other elements in the page are present/displayed
			if(isElementPresent(By.id(Home_PageObjects.SELECT_CAT))){
				logger.log(LogStatus.PASS, "verify the dropdown is displayed","Select Box is present");				

			}
			else{
				logger.log(LogStatus.FAIL, "verify the dropdown is displayed","Select Box is not present");				

			}
			
			if(isElementPresent(By.id(Home_PageObjects.INPUT_SEARCH_TEXT))){
				logger.log(LogStatus.PASS, "verify the input box is displayed","Input box is displayed");				

			}
			else{
				logger.log(LogStatus.FAIL, "verify the input box is displayed","Input box is not displayed");				

			}
			
			if(isElementPresent(By.xpath(Home_PageObjects.BTN_SEARCH))){
				logger.log(LogStatus.PASS, "verify the search button is enabled","Search button is enabled");				

			}
			else{
				logger.log(LogStatus.FAIL, "verify the input box is enabled","Search button is not enabled");				

			}
		}
	
	@Test
	public void testCase2(){
		logger = extent.startTest("testCase2");
		
		selectByVisisbleText(Home_PageObjects.SELECT_CAT, "Books");
		logger.log(LogStatus.PASS, "Drowpdown value was selected");				

		enterById(Home_PageObjects.INPUT_SEARCH_TEXT, "data-catalog");
		logger.log(LogStatus.PASS, "Required text is entered in the text box");				

		clickByXpath(Home_PageObjects.BTN_SEARCH);
		logger.log(LogStatus.PASS, "clicked on search button");	
		//Amazon.com: data-catalog: Books

		//verify the title after navigation
		if(verifyTitle("Amazon.com: data-catalog: Books")){
			logger.log(LogStatus.PASS, "verify the title after search","Title of the page is as expected");				

		}
		else{
			logger.log(LogStatus.FAIL, "verify the title after search","Title of the page is not as expected");				

		}
		


	}
	
	
	@Test
	public void testCase3(){	
		logger = extent.startTest("testCase3");

		if(driver.findElements(By.xpath(Home_PageObjects.NUM_SEARCH_RESULTS)).size()>0){
		logger.log(LogStatus.PASS, "verify the search results","Search has rendered some results");				
		clickByXpath(Home_PageObjects.LINK_FIRST_RESULT);
		logger.log(LogStatus.PASS, "clicked on first result ");				

	}
	else{
	logger.log(LogStatus.FAIL, "verify the search results","Search didn't rendered any results");				
	}
	}
	@Test
	public void testCase4(){
		logger = extent.startTest("testCase4");


		getBookDetails();
		printBookDetails();
		bd.getAuthor_Name();
		if(bd.getAuthor_Name().equalsIgnoreCase(this.author_Name)){
			logger.log(LogStatus.PASS, "Author Name of the book is "+bd.getAuthor_Name());				
	
		}
		if(bd.getPrice_Kindle_Edition()==(this.price_Kindle_Edition)){
			logger.log(LogStatus.PASS, "Price of the book is "+bd.getPrice_Kindle_Edition());				
	
		}
		if(bd.getPrice_paperback_Edition()==(this.price_paperback_Edition)){
			logger.log(LogStatus.PASS, "Price of the book for paperback is "+bd.getPrice_paperback_Edition());				
	
		}
		if(bd.getPublisher().equalsIgnoreCase(this.publisher)){
			logger.log(LogStatus.PASS, "Publisher of the book is "+bd.getPublisher());				
	
		}

		
	
	}
	
	
	
	
	public void getBookDetails(){
		this.isbn_num=getvalue(Home_PageObjects.TXT_ISBNNO);
		this.title=getvalue(Home_PageObjects.TXT_TITLE);
		this.author_Name=getvalue(Home_PageObjects.TXT_AUTHOR);
		this.price_Kindle_Edition=Double.parseDouble(getvalue(Home_PageObjects.TXT_PRICE_KINDLE).replace("$", "").trim());
		this.price_paperback_Edition=Double.parseDouble(getvalue(Home_PageObjects.TXT_PRICE_PAPERBACK).replace("$", "").trim());
		this.publisher=getvalue(Home_PageObjects.TXT_PUBLISHER);
		bookDet.put(isbn_num,new BookDeatils(isbn_num, title, author_Name, price_Kindle_Edition, price_paperback_Edition, publisher));	
	}
	
	public BookDeatils printBookDetails(){
		System.out.println(bookDet.get(isbn_num));
		bd=bookDet.get(isbn_num);
		return bd;
		//Collection<String> myBookDet=bookDet.values();
		//SearchBooks.myBookDet.iterator();
		
	}
	@BeforeTest
	public void startReport(){
		//ExtentReports(String filePath,Boolean replaceExisting) 
		//filepath - path of the file, in .htm or .html format - path where your report needs to generate. 
		//replaceExisting - Setting to overwrite (TRUE) the existing file or append to it
		//True (default): the file will be replaced with brand new markup, and all existing data will be lost. Use this option to create a brand new report
		//False: existing data will remain, new tests will be appended to the existing report. If the the supplied path does not exist, a new file will be created.
		extent = new ExtentReports ("./result/ExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
		extent
                .addSystemInfo("Host", "Assignment")
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Prashant Bajpai");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
                extent.loadConfig(new File("extent_report-config.xml"));
	}
		
	@AfterMethod
	public void getResult(ITestResult result){
		if(result.getStatus()==ITestResult.SUCCESS){
			logger.log(LogStatus.PASS, "Test Case Passed is "+result.getName());
			//logger.log(LogStatus.PASS, result.);

		}
		else if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed due to "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}
	@AfterTest
	public void endReport(){
		// writing everything to document
		//flush() - to write or update test information to your report. 
                extent.flush();
                //Call close() at the very end of your session to clear all resources. 
                //If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
                //You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
                //Once this method is called, calling any Extent method will throw an error.
                //close() - To close all the operation
                extent.close();
                driver.quit();
    }
}


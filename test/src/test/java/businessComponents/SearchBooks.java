package businessComponents;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.testng.annotations.Test;

import HelperClasses.BookDeatils;
import HelperClasses.ReusableLibrary;
import uimap.Home_PageObjects;

public class SearchBooks extends ReusableLibrary{

	
	private String isbn_num;
	private String title;
	private String author_Name;
	private Double price_Kindle_Edition;
	private Double price_paperback_Edition;
	private String publisher;

	HashMap<String,BookDeatils> bookDet=new HashMap<String,BookDeatils>();
	//BookDeatils bookDeatils=null;
	
	@Test
	public void tc1() throws InterruptedException{
		
		launchApp("chrome", "https://www.amazon.com/");
		
		//clickByJS("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[1]/div/div/i");
		//Thread.sleep(50000);
		selectByVisisbleText(Home_PageObjects.SELECT_CAT, "Books");
	}
	
	
	@Test
	public void tc2(){
		enterById(Home_PageObjects.INPUT_SEARCH_TEXT, "data-catalog");
		clickByXpath(Home_PageObjects.BTN_SEARCH);
		clickByXpath(Home_PageObjects.LINK_FIRST_RESULT);

	}
	@Test
	public void tc3(){
		getBookDetails();
		printBookDetails();
		
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
	
	public void printBookDetails(){
		System.out.println(bookDet.get(isbn_num));
		BookDeatils bd=bookDet.get(isbn_num);
		//Collection<String> myBookDet=bookDet.values();
		//SearchBooks.myBookDet.iterator();
		System.out.println(bd.getAuthor_Name());
		
	}
}

package HelperClasses;

public class BookDeatils {
	
	
	private String isbn_num;
	private String title;
	private String author_Name;
	private Double price_Kindle_Edition;
	private Double price_paperback_Edition;
	private  String publisher;
	
	
	
	public BookDeatils(String isbn_num,String title,String author_name,Double price_KD,Double price_PD,String publisher){
		this.isbn_num=isbn_num;
		this.title=title;
		this.author_Name=author_name;
		this.price_Kindle_Edition=price_KD;
		this.price_paperback_Edition=price_PD;
		this.publisher=publisher;
	}
	

	
	/**
	 * @return the isbn_num
	 */
	public String getIsbn_Num() {
		return isbn_num;
	}



	/**
	 * @param isbn_num the isbn_num to set
	 */
	public void setIsbn_Num(String isbn_num) {
		this.isbn_num = isbn_num;
	}



	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}



	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}



	/**
	 * @return the author_Name
	 */
	public String getAuthor_Name() {
		return author_Name;
	}



	/**
	 * @param author_Name the author_Name to set
	 */
	public void setAuthor_Name(String author_Name) {
		this.author_Name = author_Name;
	}



	/**
	 * @return the price_Kindle_Edition
	 */
	public Double getPrice_Kindle_Edition() {
		return price_Kindle_Edition;
	}



	/**
	 * @param price_Kindle_Edition the price_Kindle_Edition to set
	 */
	public void setPrice_Kindle_Edition(Double price_Kindle_Edition) {
		this.price_Kindle_Edition = price_Kindle_Edition;
	}



	/**
	 * @return the price_paperback_Edition
	 */
	public Double getPrice_paperback_Edition() {
		return price_paperback_Edition;
	}



	/**
	 * @param price_paperback_Edition the price_paperback_Edition to set
	 */
	public void setPrice_paperback_Edition(Double price_paperback_Edition) {
		this.price_paperback_Edition = price_paperback_Edition;
	}



	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}



	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}




}

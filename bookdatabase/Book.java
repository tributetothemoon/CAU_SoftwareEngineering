package bookdatabase;

import userdatabase.User;

public class Book{
	String title;
	int ISBN;
	String author;
	String publisher;
	int publication_year;
	String state;
	int price;
	User seller;
	
	public Book(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, User seller) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.publisher = publisher;
		this.publication_year = publication_year;
		this.price = price;
		this.seller = seller;
	}
	
	//ÃßÈÄ BookDataBase·Î ³Ñ±è
	void modify_title(String input) {this.title = input;}
	void modify_author(String input) {this.author = input;}
	void modify_ISBN(int input) {this.ISBN = input;}
}

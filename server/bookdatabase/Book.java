package server.bookdatabase;
import server.userdatabase.User;

public class Book{
	String title;
	String author;
	String publisher;
	String state;
	User seller;
	int publication_year;
	int price;
	int ISBN;
	
	public Book(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, String state, User seller) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.publisher = publisher;
		this.publication_year = publication_year;
		this.price = price;
		this.seller = seller;
		this.state = state;
	}
}

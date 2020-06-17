package server.bookdatabase;
import server.userdatabase.User;

public class Book{
	String title;
	String author;
	String publisher;
	String condition;
	User seller;
	int published_year;
	int price;
	int ISBN;
	
	public Book(String title, int ISBN, String author, String publisher, 
			int published_year, int price, String condition, User seller) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.publisher = publisher;
		this.published_year = published_year;
		this.price = price;
		this.seller = seller;
		this.condition = condition;
	}
}

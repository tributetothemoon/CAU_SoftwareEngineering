package server.bookdatabase;

public class Book{
	String title;
	String author;
	String publisher;
	String condition;
	String seller_id;
	int published_year;
	int price;
	int ISBN;
	
	public Book(String title, int ISBN, String author, String publisher, 
			int published_year, int price, String condition, String seller_id) {
		this.title = title;
		this.ISBN = ISBN;
		this.author = author;
		this.publisher = publisher;
		this.published_year = published_year;
		this.price = price;
		this.seller_id = seller_id;
		this.condition = condition;
	}
}

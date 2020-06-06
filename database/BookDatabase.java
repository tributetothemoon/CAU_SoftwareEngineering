package database;

class Book{
	private int ISBN;
	private String name;
	private String author;
	private User seller;
	
	public Book(int ISBN, String name, String author, User seller) {
		this.ISBN = ISBN;
		this.name = name;
		this.author = author;
		this.seller = seller;
	}
	
	void modify_name(String input) {this.name = input;}
	void modify_author(String input) {this.author = input;}
	void modify_ISBN(int input) {this.ISBN = input;}

}

public class BookDatabase {
	
	public BookDatabase() {	}
	
}

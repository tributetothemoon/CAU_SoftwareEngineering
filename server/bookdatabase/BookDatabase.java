package server.bookdatabase;
import java.util.ArrayList;
import server.Server;
import server.userdatabase.User;

public class BookDatabase {
	
	public static ArrayList<Book> bookList;
	
	public static void initialize() {bookList = new ArrayList<>();}
	public static int get_ISBN(Book book) {return book.ISBN;}
	public static String get_title(Book book) {return book.title;}
	public static String get_author(Book book) {return book.author;}
	public static String get_publisher(Book book) {return book.publisher;}
	public static int get_published_year(Book book) {return book.published_year;}
	public static int get_price(Book book) {return book.price;}
	public static String get_condition(Book book) {return book.condition;}
	public static User get_seller(Book book) {return book.seller;}
	
	public static void upload_book(String title, int ISBN, String author, String publisher, 
			int published_year, int price, String condition, User seller) {
		bookList.add(new Book(title, ISBN, author, publisher, 
				published_year, price, condition, seller));
	}
	public static ArrayList<Object> search_book(String title, int ISBN, String author, String seller_id) {
		ArrayList<Object> result = new ArrayList<>();		
		
		if(title != null) {
			for(Book book_title : bookList) if(book_title.title.equals(title)) result.add((Object)book_title);
		}
		else if(ISBN != 0) {
			for(Book book_ISBN : bookList) if(book_ISBN.ISBN == ISBN) result.add(book_ISBN);
		}
		else if(author != null) {
			for(Book book_author : bookList) if(book_author.author.equals(author)) result.add(book_author);
		}
		else if(seller_id != null) for(Book book_seller_id : bookList) {
			if(Server.get_id(book_seller_id.seller).equals(seller_id)) result.add(book_seller_id);
		}
		
		if(result.size() != 0) return result;
		else return null;
	}
	
	public static void modify_book(Book book, String title, int ISBN, String author, String publisher, int published_year, int price, String condition) {
		if(title != null) book.title = title;
		if(ISBN != 0) book.ISBN = 0;
		if(author != null) book.author = author;
		if(publisher != null) book.publisher = publisher;
		if(published_year != 0) book.published_year = published_year;
		if(price != 0) book.price = price;
		if(condition != null) book.condition = condition;
	}
	
	public static void delete_book(Book book) {bookList.remove(book);}
	
}

package server.bookdatabase;
import java.util.ArrayList;

public class BookDatabase {
	
	private static ArrayList<Book> bookList;
	
	public static void initialize() {
		bookList = new ArrayList<>();
		upload_book("Test Title 1", 20200625, "author_1", "publisher_1", 2020, 10000, "Fair", "test_user");
		upload_book("Test Title 2", 20200626, "author_2", "publisher_2", 2020, 20000, "Good", "test_user");
		upload_book("Test Title 3", 20200627, "author_3", "publisher_3", 2020, 30000, "Excellent", "test_user");
	}
	public static int get_ISBN(Book book) {return book.ISBN;}
	public static String get_title(Book book) {return book.title;}
	public static String get_author(Book book) {return book.author;}
	public static String get_publisher(Book book) {return book.publisher;}
	public static int get_published_year(Book book) {return book.published_year;}
	public static int get_price(Book book) {return book.price;}
	public static String get_condition(Book book) {return book.condition;}
	public static String get_seller_id(Book book) {return book.seller_id;}
	
	public static void upload_book(String title, int ISBN, String author, String publisher, 
			int published_year, int price, String condition, String seller) {
		bookList.add(new Book(title, ISBN, author, publisher, 
				published_year, price, condition, seller));
	}
	public static ArrayList<Object> search_book(String title, int ISBN, String author, String seller_id, String publisher, int published_year) {
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
			if(book_seller_id.seller_id.equals(seller_id)) result.add(book_seller_id);
		}
		else if(publisher != null) for(Book book_publisher : bookList) {
			if(book_publisher.publisher.equals(publisher)) result.add(book_publisher);
		}
		else if(published_year != 0) {for(Book book_published_year: bookList)
			if(book_published_year.published_year == published_year) result.add(book_published_year);
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
	
	public static ArrayList<Object> query_all_book() {
		ArrayList<Object> result = new ArrayList<>();
		for(Book cur : bookList) result.add(cur);
		if(result.size() != 0) return result;
		else return null;
	}
	
}

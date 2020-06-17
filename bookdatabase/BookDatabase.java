package bookdatabase;
import java.util.ArrayList;
import userdatabase.User;

public class BookDatabase {
	
	static ArrayList<Book> bookList;
	
	public static void initialize() {bookList = new ArrayList<>();}
	public static int get_ISBN(Book book) {return book.ISBN;}
	public static String get_title(Book book) {return book.title;}
	public static String get_author(Book book) {return book.author;}
	public static String get_publisher(Book book) {return book.publisher;}
	public static String get_state(Book book) {return book.state;}
	public static User get_seller(Book book) {return book.seller;}
	
	public static void upload_book(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, String state, User seller) {
		bookList.add(new Book(title, ISBN, author, publisher, 
				publication_year, price, state, seller));
	}
	public static ArrayList<Book> search_book(String title, int ISBN, String author, String seller_id) {
		ArrayList<Book> result = new ArrayList<>();
		if(title != null) for(Book book_title : bookList) if(book_title.title.equals(title)) result.add(book_title);
		else if(ISBN != 0) for(Book book_ISBN : bookList) if(book_ISBN.ISBN == ISBN) result.add(book_ISBN);
		else if(author != null) for(Book book_author : bookList) if(book_author.author.equals(title)) result.add(book_author);
		
		return null;
	}
	
	public static ArrayList<Book> search_ISBN(int ISBN){
		ArrayList<Book> result = new ArrayList<>();
		for(Book cur : bookList) if(cur.ISBN == ISBN) result.add(cur);
		
		if(result.isEmpty()) return null;
		else return result;
	}
}

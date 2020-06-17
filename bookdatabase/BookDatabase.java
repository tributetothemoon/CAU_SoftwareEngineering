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
	
	public static void upload(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, User seller) {
		bookList.add(new Book(title, ISBN, author, publisher, 
				publication_year, price, seller));
	}
	
	public static ArrayList<Book> search_ISBN(int ISBN){
		ArrayList<Book> result = new ArrayList<>();
		for(Book cur : bookList) if(cur.ISBN == ISBN) result.add(cur);
		
		if(result.isEmpty()) return null;
		else return result;
	}
}

package server;
import server.bookdatabase.*;
import server.userdatabase.*;
import java.util.ArrayList;

public class Server {
	public static void initialize() {UserDatabase.initialize();BookDatabase.initialize();}
	
	//UserDatabase access
	public static Object login(String id, String password) throws Exception {return UserDatabase.login(id, password);}
	
	public static boolean is_admin(Object account) {return UserDatabase.is_admin((User)account);}
	
	public static boolean register(String id, String password, String name, String phone, String email) {
		return UserDatabase.register(id, password, name, phone, email);
	}
	//get user information
	public static String get_id(Object account) {return UserDatabase.get_id((User)account);}
	
	public static String get_name(Object account) {return UserDatabase.get_name((User)account);}
	
	public static String get_phone(Object account) {return UserDatabase.get_phone((User)account);}
	
	public static String get_email(Object account) {return UserDatabase.get_email((User)account);}
	
	//BookDatabase access
	public static void upload_book(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, String state, Object seller) {
		BookDatabase.upload_book(title, ISBN, author, publisher, publication_year, price, state, (User)seller);
	}
	
	public static ArrayList<Object> search_book(String title, int ISBN, String author, String seller_id) {
		return BookDatabase.search_book(title, ISBN, author, seller_id);
	}
	
	//get book information
	public static int get_ISBN(Object book) {return BookDatabase.get_ISBN((Book)book);}
	
	public static String get_title(Object book) {return BookDatabase.get_title((Book)book);}
	
	public static String get_author(Object book) {return BookDatabase.get_author((Book)book);}
	
	public static String get_publisher(Object book) {return BookDatabase.get_publisher((Book)book);}
	
	public static String get_state(Object book) {return BookDatabase.get_state((Book)book);}
	
	public static String get_seller_id(Object book) {
		User seller = BookDatabase.get_seller((Book)book);
		return UserDatabase.get_id(seller);
	}
}

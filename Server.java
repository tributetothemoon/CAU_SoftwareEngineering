import bookdatabase.*;
import userdatabase.*;

public class Server {
	public static void initialize() {UserDatabase.initialize();BookDatabase.initialize();}
	
	//UserDatabase access
	public static User login(String id, String password) throws Exception {return UserDatabase.login(id, password);}
	public static boolean is_admin(User account) {return UserDatabase.is_admin(account);}
	
	public static boolean register(String id, String password, String name, String phone, String email) {
		return UserDatabase.register(id, password, name, phone, email);
	}
	//get user information
	public static String get_id(User account) {return UserDatabase.get_id(account);}
	public static String get_name(User account) {return UserDatabase.get_name(account);}
	public static String get_phone(User account) {return UserDatabase.get_phone(account);}
	public static String get_email(User account) {return UserDatabase.get_email(account);}
	
	//BookDatabase access
	
	//get book information
	public static int get_ISBN(Book book) {return BookDatabase.get_ISBN(book);}
	public static String get_title(Book book) {return BookDatabase.get_title(book);}
	public static String get_author(Book book) {return BookDatabase.get_author(book);}
	public static String get_publisher(Book book) {return BookDatabase.get_publisher(book);}
	public static String get_state(Book book) {return BookDatabase.get_state(book);}
}

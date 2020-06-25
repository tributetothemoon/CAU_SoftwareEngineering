package server;
import java.util.ArrayList;
import server.bookdatabase.*;
import server.userdatabase.*;

public class Server {
	public static void initialize() {UserDatabase.initialize();BookDatabase.initialize();}
	
	////////////////////////////User Data Function////////////////////////////////
	//get user information
	public static String get_id(Object account) {return UserDatabase.get_id((User)account);}
	public static String get_name(Object account) {return UserDatabase.get_name((User)account);}
	public static String get_phone(Object account) {return UserDatabase.get_phone((User)account);}
	public static String get_email(Object account) {return UserDatabase.get_email((User)account);}
	public static boolean is_activated(Object account) {return UserDatabase.is_activated((User)account);}
	public static boolean is_admin(Object account) {return UserDatabase.is_admin((User)account);}
	public static boolean activate(Object account) {return UserDatabase.activate((User)account);}
	public static boolean deactivate(Object account) {return UserDatabase.deactivate((User)account);}
	public static ArrayList<Object> query_all_user(){return UserDatabase.query_all_user();}
	
	public static boolean delete_user(Object account) {
		if(is_activated(account)) return false;	//account is activated. Cannot delete
		ArrayList<Object> bookList = query_book(account);
		if(bookList != null)	for(Object cur : bookList) BookDatabase.delete_book((Book)cur);	//
		return UserDatabase.delete_user((User)account);}	//true or false
	
	public static Object login(String id, String password) throws Exception {return UserDatabase.login(id, password);}
	
	public static boolean register(String id, String password, String name, String phone, String email) {
		return UserDatabase.register(id, password, name, phone, email);
	}
	
	public static String send_message(Object book) {
		String seller_id = BookDatabase.get_seller_id((Book)book);
		User seller = UserDatabase.search_user(seller_id);
		String email = UserDatabase.get_email(seller);
		if(email.equals("")) return null;
		else {
			//Some tasks to send email
			//Such as...
			//OutsideService.send_mail(email);
			return email;
		}
	}

	////////////////////////////////Book Data Function/////////////////////////////////
	
	//get book information
	public static int get_ISBN(Object book) {return BookDatabase.get_ISBN((Book)book);}	
	public static String get_title(Object book) {return BookDatabase.get_title((Book)book);}
	public static String get_author(Object book) {return BookDatabase.get_author((Book)book);}	
	public static String get_publisher(Object book) {return BookDatabase.get_publisher((Book)book);}	
	public static int get_published_year(Object book) {return BookDatabase.get_published_year((Book)book);}	
	public static int get_price(Object book) {return BookDatabase.get_price((Book)book);}	
	public static String get_condition(Object book) {return BookDatabase.get_condition((Book)book);}	
	public static String get_seller_id(Object book) {return BookDatabase.get_seller_id((Book)book);}
	public static void delete_book(Object book) {BookDatabase.delete_book((Book)book);}

	public static void upload_book(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, String state, Object seller) {
		BookDatabase.upload_book(title, ISBN, author, publisher, publication_year, price, state, UserDatabase.get_id((User)seller));
	}
	
	public static ArrayList<Object> search_book(String title, int ISBN, String author, String seller_id, String publisher, int published_year) {
		return BookDatabase.search_book(title, ISBN, author, seller_id, publisher, published_year);
	}
	
	public static ArrayList<Object> query_book(Object account){
		String user_id = UserDatabase.get_id((User)account);
		return BookDatabase.search_book(null, 0, null, user_id, null, 0);
	}
	public static ArrayList<Object> query_all_book(){return BookDatabase.query_all_book();}
	
	public static void modify_book(Object book, String title, int ISBN, String author, String publisher,
			int published_year, int price, String condition) {
		BookDatabase.modify_book((Book)book, title, ISBN, author, publisher, published_year, price, condition);
	}
	
	
}

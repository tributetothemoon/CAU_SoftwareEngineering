package userdatabase;
import java.util.ArrayList;
import bookdatabase.Book;

public class User{
	String id;
	String password;
	String name;
	String phone;
	String email;
	ArrayList<Book> stock;
	boolean activation;
	
	public User(String id, String password, String name, String phone, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.stock = new ArrayList<>();
		this.activation = true;
	}
	
	public String get_id() {return this.id;}
}

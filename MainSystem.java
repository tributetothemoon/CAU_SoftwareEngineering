import bookdatabase.BookDatabase;
import userdatabase.UserDatabase;

public class MainSystem {
	public MainSystem() {}
	
	public static boolean login(String id, String password) {return UserDatabase.login(id, password);}
	public static boolean register(String id, String password, String name, String phone, String email) {
		return UserDatabase.register(id, password, name, phone, email);
	}
}

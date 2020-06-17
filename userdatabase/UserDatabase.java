package userdatabase;
import java.util.ArrayList;

public class UserDatabase {
	static ArrayList<User> userList;
	static User admin_account;
	
	public static String get_id(User account) {return account.id;}
	public static String get_name(User account) {return account.name;}
	public static String get_phone(User account) {return account.phone;}
	public static String get_email(User account) {return account.email;}
	public static boolean is_admin(User account) {return admin_account==account;}
	
	public static void initialize(){
		userList = new ArrayList<>();
		admin_account = new User("admin", "nayana", "administrator", "None", "None");
		userList.add(admin_account);
	}
	
	public static boolean register(String id, String password, String name, String phone, String email) {
		for(User cur : userList) if(cur.id.equals(id)) return false;
		userList.add(new User(id, password, name, phone, email));
		return true;
	}
	
	public static User login(String id, String password) throws Exception {
		for(User cur : userList) {
			if(cur.id.equals(id)) {
				if(cur.password.equals(password)) {
					if(cur.activation) return cur;//log in succeeded
					else throw new Exception("Account has been deactivated");
				}
				else {
					throw new Exception("Incorrect password");
				}
			}
		}throw new Exception("There's no such a account");	//no id in the database
	}
}

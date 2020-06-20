package server.userdatabase;
import java.util.ArrayList;

public class UserDatabase {
	static ArrayList<User> userList;
	static User admin_account;
	
	public static String get_id(User account) {return account.id;}
	public static String get_name(User account) {return account.name;}
	public static String get_phone(User account) {return account.phone;}
	public static String get_email(User account) {return account.email;}
	public static boolean is_activated(User account) {return account.activation;}
	public static boolean is_admin(User account) {return admin_account==account;}
	public static User search_user(String id) {	
		for(User cur : userList) if(cur.id.equals(id)) return cur;
		return null;
	}
	public static boolean activate(User account) {
		if(account.activation) return false;
		else account.activation = true;
		return true;
	}
	public static boolean deactivate(User account) {
		if(!account.activation) return false;
		else account.activation = false;
		return true;
	}
	public static boolean delete_user(User account) {
		if(account.activation) return false;
		userList.remove(account);
		return true;
	}
	
	public static void initialize(){
		userList = new ArrayList<>();
		admin_account = new User("admin", "nayana", "administrator", "None", "None");
		userList.add(admin_account);
		userList.add(new User("kkm733", "rlans8010", "kimoon", "010-8606-5884", "moon_hf@naver.com"));
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
	
	public static ArrayList<Object> query_all_user(){
		ArrayList<Object> result = new ArrayList<>();
		for(User cur : userList) result.add(cur);
		if(result.size() != 0) return result;
		else return null;
	}
}

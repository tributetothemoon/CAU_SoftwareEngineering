package userdatabase;
import java.util.ArrayList;

public class UserDatabase {
	
	static ArrayList<User> userList;
	
	public static boolean register(String id, String password, String name, String phone, String email) {
		for(User cur : userList) if(cur.id.equals(id)) {return false;}
		
		userList.add(new User(id, password, name, phone, email));
		return true;
	}
	
	public static boolean login(String id, String password) {
		for(User cur : userList) if(cur.id.equals(id)) {
			if(cur.password.equals(password)) return true;
			else return false;
		}
		return false;
	}

}

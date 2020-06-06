import database.*;

public class System {
	
	private BookDatabase bookdb;
	private UserDatabase userdb;
	
	public System() {
		bookdb = new BookDatabase();
		userdb = new UserDatabase();
	}
	
}

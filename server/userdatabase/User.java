package server.userdatabase;

public class User {
	String id;
	String password;
	String name;
	String phone;
	String email;
	boolean activation;
	
	public User(String id, String password, String name, String phone, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.activation = true;
	}
}
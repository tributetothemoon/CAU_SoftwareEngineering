import java.util.Scanner;
import java.util.ArrayList;
import bookdatabase.*;
import userdatabase.*;

enum state {TERMINATING, STARTUP, LOGGEDIN, ADMIN}

public class ClientProgram {
	static Scanner scan;
	static User account;
	static ArrayList<Book> displayBookList;
	static state program_state;
	
	public static void clear() {for(int i = 0; i < 50; i++) System.out.println();}
	
	public static void login() {
		scan.nextLine();	//clear buffer
		
		System.out.print("ID : ");
		String id = scan.next();
		System.out.print("Password : ");
		String password = scan.next();
		
		try {
			account = Server.login(id, password);
			
			if(Server.is_admin(account)) {
				program_state = state.ADMIN;
				System.out.println("Running in administrator mode");
			}else {
				program_state = state.LOGGEDIN;
				System.out.printf("Welcome, %s!\n", Server.get_name(account));
			}
			
		}catch(Exception e) {
			System.out.printf("Cannot log in : %s\n", e.getMessage());
		}		
	}
	
	public static void logout() {
		program_state = state.STARTUP;
		account = null;
	}
	
	public static void register() {
		String id;
		String password;
		String name;
		String phone;
		String email;
		
		scan.nextLine();	//clear buffer
		
		System.out.print("ID : ");
		id = scan.next();
		scan.nextLine();
		System.out.print("PW : ");
		password = scan.next();
		scan.nextLine();
		System.out.print("Your name : ");
		name = scan.nextLine();
		System.out.print("Your Phone Number : ");
		phone = scan.nextLine();
		System.out.print("Your Email : ");
		email = scan.nextLine();
		
		if(Server.register(id, password, name, phone, email)) {
			System.out.println("Registration succeed. Log in your account.");
		}else {
			System.out.println("Registration failed. Please use another ID.");
		}
	}
	
	public static void startup_menu() {
		System.out.println("[Menu]");
		System.out.println("1. Log-in");
		System.out.println("2. Register");
		System.out.println("0. Exit");
		System.out.printf("Selection : ");
		
		int menu = scan.nextInt();
		switch(menu){
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			case 0:
				program_state = state.TERMINATING;
				break;
			default:
				break;
		}
	}
	
	public static void user_menu() {
		System.out.println("[Menu]");
		System.out.println("1. Upload");
		System.out.println("2. Search");
		System.out.println("9. Logout");
		System.out.println("0. Exit program");
		System.out.printf("Selection : ");
		int menu = scan.nextInt();
		switch(menu){
		case 1:
			break;
		case 2:
			break;
		case 9:
			program_state = state.STARTUP;
			break;
		case 0:
			program_state = state.TERMINATING;
			break;
		default:
			break;
		}
	}
	
	public static void admin_menu() {
		
	}
	
	public static void main(String[] args) {
		scan = new Scanner(System.in);
		account = null;
		program_state = state.STARTUP;
		Server.initialize();
		
		System.out.println("Welcome to Used-Book Marketplace");
		
		while(program_state != state.TERMINATING) {
			
			switch(program_state) {
			
			case STARTUP:
				startup_menu();
				break;
			case LOGGEDIN:
				user_menu();
				break;
			case ADMIN:
				System.out.println("You are a admin!");
				program_state = state.STARTUP;
				break;
			default:
				break;
			}
			
		}
		scan.close();
		System.out.println("The program has been terminated");
	}

}

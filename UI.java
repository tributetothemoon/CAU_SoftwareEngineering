import java.util.Scanner;
import java.util.ArrayList;
import bookdatabase.*;
import userdatabase.*;

enum state {TERMINATING, STARTUP, LOGGEDIN, ADMIN}

public class UI {
	static Scanner scan;
	static User loggedInAccount;
	static ArrayList<Book> displayBookList;
	static state program_state;
	
	public static void clear() {for(int i = 0; i < 50; i++) System.out.println();}
	
	public static void login() {
		System.out.println("Debuging : Login run well!");
		return;
	}
	
	public static void register() {
		String id;
		String password;
		String name;
		String phone;
		String email;
		
		System.out.print("ID : ");
		id = scan.next();
		System.out.print("PW : ");
		password = scan.next();
		System.out.print("Your name : ");
		name = scan.next();
		System.out.print("Your Phone Number : ");
		phone = scan.next();
		System.out.print("Your Email : ");
		email = scan.next();
		
		if(MainSystem.register(id, password, name, phone, email)) {
			System.out.println("Registration succeed.");
		}else {
			System.out.println("Registration failed. Please use other ID.");
		}
		
		return;
	}
	
	public static void startup() {
		System.out.println("[Menu]");
		System.out.println("1. Log-in");
		System.out.println("2. Register");
		System.out.println("0. Exit");
		
		System.out.printf("\nSelection : ");
		int menu;
		menu = scan.nextInt();
		
		switch(menu) {
		case 1:
			login();
			program_state = state.LOGGEDIN;
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
		
	}
	
	public static void admin_menu() {
		
	}
	
	public static void main(String[] args) {
		program_state = state.STARTUP;
		loggedInAccount = null;
		
		System.out.println("Welcome to Used-Book Marketplace");
		
		while(program_state !=	state.TERMINATING) {
			if(loggedInAccount == null) {
				startup();
			}else if(loggedInAccount.get_id().equals("admin")) {
				//some admin menu
			}else {
				//some logged in menu
			}
		}
		scan.close();
		System.out.println("Shut down the program, good bye-");
	}

}

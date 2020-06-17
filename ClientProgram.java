import java.util.Scanner;
import java.util.ArrayList;
import bookdatabase.Book;
import userdatabase.User;


enum state {TERMINATING, STARTUP, LOGGEDIN, ADMIN}

public class ClientProgram {
	private static final int NULL = 0;
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
	
	public static void upload_book() {
		String title;
		String author = null;
		String publisher = null;
		String state = null;
		int publication_year = 0;
		int price = 0;
		int ISBN = 0;
		int select = 0;
		
		String buffer;
		
		scan.nextLine();	//clear buffer
		
		System.out.print("Title : ");
		title = scan.nextLine();
		System.out.print("Author (Type enter to skip) : ");
		author = scan.nextLine();
		System.out.print("Publisher (Type enter to skip) : ");
		publisher = scan.nextLine();
		try {
			System.out.print("Publicication Year (Type enter to skip) : ");
			buffer = scan.nextLine();
			if(!buffer.equals("")) publication_year = Integer.parseInt(buffer);
			System.out.print("Price (Type enter to skip) : ");
			buffer = scan.nextLine();
			if(!buffer.equals("")) price = Integer.parseInt(buffer);
			System.out.print("ISBN (Type enter to skip) : ");
			buffer = scan.nextLine();
			if(!buffer.equals("")) ISBN = Integer.parseInt(buffer);
			
			System.out.print("State? 1. Excellent / 2. Good / 3. Fair (Type enter to skip)\n");
			while(0 == select && 3 < select) {
				System.out.print("Select : ");
				buffer = scan.nextLine();
				
				if(!buffer.equals("")) select = Integer.parseInt(buffer);
				else { state = buffer.toString(); break; }
				
				switch(select) {
				case 1 : state = "Excellent";	break;
				case 2 : state = "Good";		break;
				case 3 : state = "Fair";		break;
				default : System.out.println("Please choose between 1~3");	break;
				}
			}
		}catch(Exception e) {
			System.out.println("Wrong format of information. Try again");
			return;
		}
		
		Server.upload_book(title, ISBN, author, publisher, publication_year, price, state, account);
		System.out.println("Book has been uploaded sucessfully.");
		
	}
	
	public static void search_book(){
		String title = null;
		String author = null;
		String seller = null;
		int ISBN = NULL;
		int menu = 0;
		
		System.out.println("Keyword for search");
		System.out.println("1. Title");
		System.out.println("2. Author");
		System.out.println("3. ISBN");
		System.out.println("4. Seller's ID");
		System.out.println("0. Quit");
		
		try {
			menu = scan.nextInt();
			
			switch(menu) {
			case 1:	title = scan.nextLine();	break;
			case 2: author = scan.nextLine();	break;
			case 3: ISBN = scan.nextInt();		break;
			case 4: seller = scan.nextLine();	break;
			default:	break;
			}
		}catch(Exception e) {
			System.out.println("Please type correct number");
			return;
		}
		displayBookList = Server.search_book(title, ISBN, author, seller);
	}
	
	public static void user_menu() {
		System.out.println("[Menu]");
		System.out.println("1. Upload");
		System.out.println("2. Search");
		System.out.println("9. Logout");
		System.out.println("0. Exit program");
		System.out.printf("Selection : ");
		
		int menu;
		
		try {
			menu = scan.nextInt();
		}catch(Exception e) {
			System.out.println("Please use correct number to choose menu.");
			return;
		}
		
		switch(menu){
		case 1:
			upload_book();
			break;
		case 2:
			search_book();
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

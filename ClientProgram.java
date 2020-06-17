import java.util.Scanner;
import java.util.ArrayList;
import server.*;

enum state {TERMINATING, STARTUP, LOGGEDIN, ADMIN}

public class ClientProgram {
	private static final int NULL = 0;
	static Scanner scan;
	static Object account;
	static ArrayList<Object> bookList;
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
		System.out.printf("Select : ");
		
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
	
	public static void print_book_info(Object book) {
		int temp;
		System.out.println("Title : " + Server.get_title(book));
		System.out.println("Publisher : " + Server.get_publisher(book));
		System.out.println("Author : " + Server.get_author(book));
		System.out.print("Published Year : ");
		temp = Server.get_published_year(book);
		if(temp != NULL) System.out.print(temp);
		System.out.println();
		System.out.print("Price : ");
		temp = Server.get_price(book);
		if(temp != NULL) System.out.print(temp);
		System.out.println();
		System.out.println("Condition : " + Server.get_condition(book));
	}
	
	public static void upload_book() {
		String title;
		String author = null;
		String publisher = null;
		String condition = null;
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
			while(0 == select || 3 < select) {
				
				System.out.print("Select : ");
				buffer = scan.nextLine();
				
				if(!buffer.equals("")) select = Integer.parseInt(buffer);
				else { condition = buffer.toString(); break; }
				
				switch(select) {
				case 1 : condition = "Excellent";	break;
				case 2 : condition = "Good";		break;
				case 3 : condition = "Fair";		break;
				default : System.out.println("Please choose between 1~3");	break;
				}
			}
		}catch(Exception e) {
			System.out.println("Wrong format of information. Try again");
			return;
		}
		
		Server.upload_book(title, ISBN, author, publisher, publication_year, price, condition, account);
		System.out.println("Book has been uploaded sucessfully.");
		
	}
	
	public static void search_book(){
		String title = null;
		String author = null;
		String seller = null;
		int ISBN = NULL;
		int menu = 0;
		String buffer;
		
		System.out.println("Keyword for search");
		System.out.println("1. Title");
		System.out.println("2. Author");
		System.out.println("3. ISBN");
		System.out.println("4. Seller's ID");
		System.out.println("0. Quit");
		System.out.print("Select : ");
		
		try {
			
			menu = scan.nextInt();
			scan.nextLine();	//clear buffer
			System.out.print("Keyword : ");
			buffer = scan.nextLine();
			
			switch(menu) {
			case 1:	title = buffer;	break;
			case 2: author = buffer;	break;
			case 3: ISBN = Integer.parseInt(buffer);		break;
			case 4: seller = buffer;	break;
			case 0: return;
			default:	break;
			}
			
		}catch(Exception e) {
			System.out.println("Please type correct number");
			return;
		}
		
		bookList = Server.search_book(title, ISBN, author, seller);
		
		System.out.println("[Serching Result]");
		int index = 1;
		
		if(bookList!=null)	for(Object cur : bookList) {
			System.out.printf("[Index : %d]\n", index++);
			print_book_info(cur);
			System.out.println("Seller ID : " + Server.get_seller_id(cur));
			System.out.println();
		}
		else {
			System.out.println("There's no such a book");
			return;
		}
		send_message();
	}
	
	public static void send_message() {
		System.out.print("Type index to send message to seller for buying(0 to exit) : ");
		int index = 0;
		try {
			index = scan.nextInt();
		}catch(Exception e) {
			System.out.println("Please type correct number");
		}
		if(index == 0) return;	//exit
		else if(bookList.size() < index) {System.out.println("Incorrect index"); return;}	//out of index
		
		System.out.println("Email has been sended to " + Server.get_seller_id(bookList.get(index-1)) + ", " +  Server.send_message(bookList.get(index - 1)));
	}
	
	public static void query_client_book() {
		bookList = Server.query_book(account);
		
		if(bookList == null) {
			System.out.println("There's no book for sale");
			return;
		}else {
			int index = 1;
			System.out.println();
			for(Object cur : bookList) {
				System.out.printf("[Index : %d]\n", index++);
				print_book_info(cur);
				System.out.println();
			}
		}
		
		int menu = 0;
		
		System.out.println("[Menu]");
		System.out.println("1 : Edit information of book");
		System.out.println("2 : Delete book");
		System.out.println("0 : Exit");
		System.out.print("Select : ");
		try {
			menu = scan.nextInt();
		}catch(Exception e) {
			System.out.println("Please type correct number");
			return;
		}
		switch(menu) {
		case 1:
			modify_book();
			break;
		case 2:
			delete_book();
			break;
		case 0:
			break;
		default:
			break;
		}
	}
	
	public static void modify_book() {
		String title = null;
		int ISBN = NULL;
		String author = null;
		String publisher = null;
		int published_year = NULL;
		int price = NULL;
		String condition = null;
		
		System.out.print("Type index of book to modify information(0 to exit) : ");
		int index = 0;
		try {
			index = scan.nextInt();
		}catch(Exception e) {
			System.out.println("Please type correct number");
		}
		if(index == 0) return;	//exit
		else if(bookList.size() < index) {System.out.println("Incorrect index"); return;}	//out of index
		
		Object book = bookList.get(index-1);
		
		System.out.println("[information to change]");
		System.out.println("1. Title");
		System.out.println("2. ISBN");
		System.out.println("3. Author");
		System.out.println("4. Publisher");
		System.out.println("5. Published Year");
		System.out.println("6. Price");
		System.out.println("7. Condition");
		System.out.println("0. Quit");
		System.out.printf("Select : ");
		
		int menu = scan.nextInt();
		scan.nextLine();	//clear buffer
		
		try {
			System.out.print("Type information to change : ");
			switch(menu){
			case 1:
				title = scan.nextLine();
				break;
			case 2:
				ISBN = scan.nextInt();
				break;
			case 3:
				author = scan.nextLine();
				break;
			case 4:
				publisher = scan.nextLine();
				break;
			case 5:
				published_year = scan.nextInt();
				break;
			case 6:
				price = scan.nextInt();
				break;
			case 7:
				int select = 0;
				System.out.print("State? 1. Excellent / 2. Good / 3. Fair (Type enter to skip)\n");
				System.out.print("Select : ");
				
				switch(select) {
				case 1 : condition = "Excellent";	break;
				case 2 : condition = "Good";		break;
				case 3 : condition = "Fair";		break;
				default : System.out.println("Please choose between 1~3");	break;
				}
				break;
			default:
				break;
			}
		}catch(Exception e) {
			System.out.println("Wrong form of information");
			return;
		}
		Server.modify_book(book, title, ISBN, author, publisher, published_year, price, condition);
	}
	
	public static void delete_book() {
		System.out.print("Type index of book to delete(0 to exit) : ");
		int index = 0;
		try {
			index = scan.nextInt();
		}catch(Exception e) {
			System.out.println("Please type correct number");
		}
		if(index == 0) return;	//exit
		else if(bookList.size() < index) {System.out.println("Incorrect index"); return;}	//out of index
		
		Server.delete_book(bookList.get(index-1));
		
	}
	
	public static void user_menu() {
		System.out.println("[Menu]");
		System.out.println("1. Upload");
		System.out.println("2. Search");
		System.out.println("3. Your book for sale");
		System.out.println("9. Logout");
		System.out.println("0. Exit program");
		System.out.printf("Select : ");
		
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
		case 3:
			query_client_book();
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

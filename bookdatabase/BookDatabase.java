package bookdatabase;
import java.util.ArrayList;
import userdatabase.User;

public class BookDatabase {
	
	static ArrayList<Book> bookList;
	
	public static void upload(String title, int ISBN, String author, String publisher, 
			int publication_year, int price, User seller) {
		bookList.add(new Book(title, ISBN, author, publisher, 
				publication_year, price, seller));
	}
	
	public static ArrayList<Book> search_ISBN(int ISBN){
		ArrayList<Book> result = new ArrayList<>();
		for(Book cur : bookList) if(cur.ISBN == ISBN) result.add(cur);
		
		if(result.isEmpty()) return null;
		else return result;
	}
}

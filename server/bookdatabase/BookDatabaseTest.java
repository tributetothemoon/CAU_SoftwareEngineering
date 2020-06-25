package server.bookdatabase;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookDatabaseTest {

	@BeforeEach
	void setUp() throws Exception {
		BookDatabase.initialize();
		BookDatabase.upload_book("Test Title 1", 20200625, "Test Author 1", "Test Publisher 1", 2020, 10000, "Fair", "test_user");
		BookDatabase.upload_book("Test Title 2", 20200626, "Test Author 2", "Test Publisher 2", 2020, 20000, "Good", "test_user");
		BookDatabase.upload_book("Test Title 3", 20200627, "Test Author 3", "Test Publisher 3", 2020, 30000, "Excellent", "test_user");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void search_test() {
		ArrayList<Object> testList = new ArrayList<>();
		Book temp;
		
		//Search by ID
		testList = BookDatabase.search_book(null, 0, null, "test_user", null, 0);
		
		temp = (Book)testList.get(0);
		assertEquals(temp.title, "Test Title 1");
		
		temp = (Book)testList.get(1);
		assertEquals(temp.title, "Test Title 2");
		
		temp = (Book)testList.get(2);
		assertEquals(temp.title, "Test Title 3");
		
		//Search by ISBN
		testList = BookDatabase.search_book(null, 20200627, null, null, null, 0);
		
		temp = (Book)testList.get(0);
		assertEquals(temp.title, "Test Title 3");
		
		//Search by Author
		testList = BookDatabase.search_book(null, 0, "Test Author 2", null, null, 0);
		
		temp = (Book)testList.get(0);
		assertEquals(temp.title, "Test Title 2");
		
		//Search by Publisher
		testList = BookDatabase.search_book(null, 0, null, null, "Test Publisher 2", 0);
		
		temp = (Book)testList.get(0);
		assertEquals(temp.title, "Test Title 2");
		
		//Search by Published Year
		testList = BookDatabase.search_book(null, 0, null, null, null, 2020);
		
		temp = (Book)testList.get(0);
		assertEquals(temp.title, "Test Title 1");
		
		temp = (Book)testList.get(1);
		assertEquals(temp.title, "Test Title 2");
		
		temp = (Book)testList.get(2);
		assertEquals(temp.title, "Test Title 3");
	}

}

import java.util.Scanner;
import java.util.List;
public class LibraryManagement{

	DatabaseManager dbManager=new DatabaseManager();
	LibraryManager libraryManager=new LibraryManager();
	DateValidation date=new DateValidation();
	Input input=new Input();
	Scanner scan=new Scanner(System.in);
	List list=null;
	public static void main(String[]args){
		LibraryManagement lm=new LibraryManagement();
		lm.libraryMenu();

	}
	
	public void libraryMenu(){
		libraryManager.updateReservations();
		boolean check=true;
		while(check==true){
			System.out.print("|=======================================================|\n"+
					 "|               LIBRARY MANAGEMENT SYSTEM               |\n"+
					 "|=======================================================|\n"+ 
					 "|                       MAIN MENU                       |\n"+
					 "|=======================================================|\n"+
					 "|   1.Add Books                                         |\n"+
					 "|   2.Add Members                                       |\n"+
					 "|   3.Search Books By Category                          |\n"+
					 "|   4.Search Books By Name                              |\n"+
					 "|   5.Search Books By Authors                           |\n"+
					 "|   6.Member and book borrowing Details                 |\n"+
					 "|   7.Exit                                              |\n"+
					 "|=======================================================|\n"+
					 "    Select the option  :  ");
			int dec=input.getInt("");
			System.out.print("|=======================================================|\n\n\n");
			switch(dec){
			
				case 1:
					addBook();
					break;
				case 2:
					addMember();
					break;
				case 3:
					booksCategory();
					break;
				case 4:
					isBookAvailable();
					break;
				case 5:
					isAuthorsBookAvailable();
					break;
				case 6:
					memberDetails();
					break;
				case 7:
					check=false;
					break;
				default:
					System.out.println("Enter Valid Input..");
			}
		}
	}
	
	public void memberDetails(){
		boolean check=true;
		int id=input.getInt("Enter Member Id : ");
		while(check==true){
			System.out.print("|=======================================================|\n"+
					 "|               LIBRARY MANAGEMENT SYSTEM               |\n"+
					 "|=======================================================|\n"+ 
					 "|            Member & Book Borrowing Details            |\n"+
					 "|=======================================================|\n"+
					 "|   1.Borrow books                                      |\n"+
					 "|   2.Reserve books                                     |\n"+
					 "|   3.Return books                                      |\n"+
					 "|   4.Member details                                    |\n"+
					 "|   5.Member History                                    |\n"+
					 "|   6.View Barrowed Books                               |\n"+
					 "|   7.Main Menu                                         |\n"+
					 "|=======================================================|\n"+
					 "    Select the option  :  ");
			int dec=input.getInt("");
			System.out.print("|=======================================================|\n\n\n");
			switch(dec){
				case 1:
					borrowBook(id);
					break;
				case 2:
					reserveBook(id);
					break;
				case 3:
					returnBook(id);
					break;
				case 4:
					System.out.println(libraryManager.getMember(id)+"\n\n\n");
					break;
				case 5:
	         System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Id","Book Name","Author Name","Barrowed Date","Due Date"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				          list=libraryManager.getTransactionHistory(id);  
				          for(Object al:list)  {         
						System.out.print(al+"\n");
					  }
		System.out.println("-------------------------------------------------------------------------------------------------------------------------\n\n\n");
					break;
				case 6:
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Id","Book Name","Author Name","Barrowed Date","Due Date"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				           list=libraryManager.getTransactions(id);
				           for(Object al:list)  {         
					 	System.out.print(al+"\n");
				      	   }
		System.out.println("-------------------------------------------------------------------------------------------------------------------------\n\n\n");
					break;
				case 7:
					check=false;
					break;
				default:
					System.out.println("Enter Valid Input..");
			}
		}
	}
	
	public void addBook(){
		input.getString("");
		String name=input.getString("Enter book name       : ");
		String author=input.getName("Enter Author name     : ");
		String category=input.getString("Enter Category name   : ");
		int year=input.getInt("Enter published year  : ");
		int bookCount=input.getInt("Enter number of books : ");
		
	        libraryManager.addStock(libraryManager.getBookId(name),libraryManager.getAuthorId(author),libraryManager.getCategoryId(category),year,bookCount);
	}
	
	public void addMember(){
		input.getString("");
		String name=input.getName("Enter member's name       : ");
		int age=input.getInt("Enter members's age       : ");
		input.getString("");
		String address=input.getString("Enter a member Address    : ");
		long mobileNo=input.getPhoneNo("Enter member's contact-no : ");
		int id=dbManager.addMember(name,age,address,mobileNo);
		System.out.println("The member Id is        : "+id);
	}
	
	public void isBookAvailable(){
		input.getString("");
		String name=input.getString("Enter book name       : ");
		list=libraryManager.getBooks(name);
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Book Id","Book Name","Author Name","Category Name","Year"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		if(!(list).isEmpty()){
			 for(Object al:list)  {         
					 	System.out.print(al+"\n");
				      	   }
		System.out.println("-------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
		else{
		System.out.print("                                       There is no book that named with "+name+"                                   \n"+
				  "-------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
	}
	
	public void isAuthorsBookAvailable(){
		input.getString("");
		String name=input.getName("Enter Author name     : ");
		list=libraryManager.getAuthorBooks(name);
		 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Book Id","Book Name","Author Name","Category Name","Year"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		if(!(list).isEmpty()){
			 for(Object al:list)  {         
					 	System.out.print(al+"\n");
				      	   }
		System.out.println("------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
		else{
			System.out.print("                                       There is no author that named with "+name+"                                   \n"+
				  "--------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
	}
	
	public void booksCategory(){
		input.getString("");
		String category=input.getString("Enter Category's name : ");
		list=libraryManager.getCategoryBooks(category);
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Book Id","Book Name","Author Name","Category Name","Year"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
		if(!(list).isEmpty()){
			 for(Object al:list)  {         
					 	System.out.print(al+"\n");
				      	   }
		System.out.println("-------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
		else{
			System.out.print("                                       There is no book that categorized with "+category+"                                   \n"+
				  "-------------------------------------------------------------------------------------------------------------------------\n\n\n");
		}
	}

	public void borrowBook(int memberId){
		if(dbManager.getBookLimit(memberId)>0){
		input.getString("");
		String book=input.getString("Enter book name       : ");
		String author=input.getName("Enter Author name     : ");
		if(libraryManager.bookIsReserved(libraryManager.getBookId(book),libraryManager.getAuthorId(author),memberId)){
			issueReservedBook(book,author,memberId);
		}
		else{
			issueBook(book,author,memberId);
		}
		}
		else{
			System.out.println("!!Your Book Limit is Full..you didn't Borrow Any books.");
		}
	}
	
	public void issueReservedBook(String book,String author,int memberId){
			int dec=input.getInt("The book is already reserved by this member\n Press 1 to issue the book Else Press any : ");
			if(dec==1){
				String date=input.getDate("Enter Issued date (yyyy-MM-dd) : ");
				int stockId=libraryManager.getStockId(libraryManager.getBookId(book),libraryManager.getAuthorId(author),memberId);
				dbManager.issueBook(memberId,stockId,libraryManager.getBookId(book),date);
				libraryManager.updateBookLimit(memberId,-1);
				int reservationId=libraryManager.getReservationId(libraryManager.getBookId(book),libraryManager.getAuthorId(author),memberId);
				libraryManager.updateStockStatus(stockId,0);
				libraryManager.updateReservation(reservationId,0);
				System.out.println("Book successfully Issued...");
			}
	}
	
	public void issueBook(String book,String author,int memberId){
		Stock stock=libraryManager.getBook(book,author);
		if(stock!=null){
			int dec=input.getInt("Press 1 to Issue the book else Press Any  : ");
			if(dec==1){
				String date=input.getDate("Enter Issued date (yyyy-MM-dd) : ");
				dbManager.issueBook(memberId,stock.getStockId(),stock.getBookId(),date);
				libraryManager.updateBookLimit(memberId,-1);
				libraryManager.updateStockStatus(stock.getStockId(),0);
				System.out.println("Book successfully Issued...");
			}
		}
		else{
			System.out.println("The given author's book is not available");
		}
	}
	
	public void reserveBook(int memberId){
	 	input.getString("");
		String book=input.getString("Enter book name       : ");
		String author=input.getName("Enter Author name     : ");
		Stock stock=libraryManager.getBook(book,author);
		if(stock==null){
			int dec=input.getInt("Press 1 to Reserve the book else Press Any  : ");
			if(dec==1){
				dbManager.reserveBook(libraryManager.getBookId(book),libraryManager.getAuthorId(author),memberId);
				System.out.println("Book Reserved Successfully...");
			}
		}
		else{
			System.out.println("The given author's book is available");
		}
	}
	
	public void returnBook(int memberId){
		System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                 System.out.println(String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|","Id","Book Name","Author Name","Barrowed Date","Due Date"));
                 System.out.println("-------------------------------------------------------------------------------------------------------------------------");
				           list=libraryManager.getTransactions(memberId);
				           for(Object al:list)  {         
					 	System.out.print(al+"\n");
				      	   }
		System.out.println("-------------------------------------------------------------------------------------------------------------------------\n\n\n");
		int transactionId=input.getInt("Select transactions id to return the book : ");
		if(libraryManager.isTransactionIdExists(memberId,transactionId)){
			int dec=input.getInt("Press 1 to buy any penalty charges else press any to return the book : ");
			if(dec==1){
				Barrower barrower=libraryManager.getTransaction(transactionId);
				chargePenalty(memberId,barrower.getStockId());
				returnBook(memberId,transactionId);
			}
			else{
				returnBook(memberId,transactionId);
			}
		}
		else{
			System.out.println("The given Id is not present in the above list..");
		}
	}
	
	public void returnBook(int memberId,int transactionId){
		Barrower barrower=libraryManager.getTransaction(transactionId);
				if(libraryManager.bookIsReserved(libraryManager.getBookId(barrower.getBookName()),libraryManager.getAuthorId(barrower.getAuthorName()))){
	  			int reservationId=libraryManager.getReservationId(libraryManager.getBookId(barrower.getBookName()),libraryManager.getAuthorId(barrower.getAuthorName()));
				dbManager.updateReservation(reservationId,date.getCurrentDate(),barrower.getStockId());	
				dbManager.updatetransactions(transactionId,date.getCurrentDate());
				libraryManager.updateBookLimit(memberId,1);
				libraryManager.updateStockStatus(barrower.getStockId(),2);
				}
				else{
					dbManager.updatetransactions(transactionId,date.getCurrentDate());
					libraryManager.updateBookLimit(memberId,1);
					libraryManager.updateStockStatus(barrower.getStockId(),1);
				}
	}
	
	public void chargePenalty(int memberId,int stockId){
		input.getString("");
		String reason=input.getString("Enter Reason : ");
		int penalty=input.getInt("Enter penalty amount : Rs.");
		int dec=input.getInt("presss 1 to pay the penalty charge else press any : ");
		if(dec==1){
			dbManager.chargePenalty(memberId,stockId,reason,penalty);
		}
	}
}












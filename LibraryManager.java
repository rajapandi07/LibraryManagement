import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LibraryManager{
	
	DatabaseManager dbManager=new DatabaseManager();
	DateValidation date=new DateValidation();
	ResultSet rs=null;
	List list=null;
	public int getBookId(String name){
		try{
		rs=dbManager.getBookId(name);
			if(rs.next()){
			return rs.getInt(1);
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		
		return dbManager.addBook(name);
	}
	
	
	public int getAuthorId(String name){
	
		try{
		rs=dbManager.getAuthorId(name);
			if(rs.next()){
			return rs.getInt(1);
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                
		return dbManager.addAuthor(name);
	}
	
	public int getCategoryId(String name){
		try{
		rs=dbManager.getCategoryId(name);
			if(rs.next()){
				return rs.getInt(1);
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return dbManager.addCategory(name);
	}
	
	public void addStock(int bookId,int authorId,int categoryId,int year,int bookCount){
		for(int i=0;i<bookCount;i++){
			dbManager.addStock(bookId,authorId,categoryId,year);
		}
		dbManager.updateBook(bookId,bookCount);
	}
	
	public List getBooks(String bookName){
		ArrayList<String> al=getAuthors(bookName);
		list=new ArrayList();
		try{
		for(String author:al){
			rs=dbManager.getBook(author,bookName);
			rs.next();
			list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return list;
	}
	
	public ArrayList getAuthors(String bookName){
		ArrayList<String>al=new ArrayList();
		try{
		rs=dbManager.getAuthors(bookName);
		while(rs.next()){
			al.add(rs.getString(1));
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return al;
	}
	
	public List getAuthorBooks(String author){
		ArrayList<String> al=getAuthorBook(author);
		list=new ArrayList();
		try{
		for(String bookName:al){
			rs=dbManager.getBook(author,bookName);
			rs.next();
			list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return list;
	}
	
	public ArrayList getAuthorBook(String author){
		ArrayList<String>al=new ArrayList();
		try{
			rs=dbManager.getBook(author);
			while(rs.next()){
				al.add(rs.getString(1));
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return al;
	}
	
	public List getCategoryBooks(String category){
		ArrayList<String> al=getCategoryBook(category);
		ArrayList<String>bk=new ArrayList();
		ArrayList<String>at=new ArrayList();
		list=new ArrayList();
		try{
		for(String bookName:al){
			rs=dbManager.getCategoryBook(bookName);
			while(rs.next()){
			if(!(bk.contains(rs.getString(2)) && at.contains(rs.getString(3)))){
			list.add(new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5)));
			}
			bk.add(rs.getString(2));
			at.add(rs.getString(3));
		}
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return list;
	}
	
	public ArrayList getCategoryBook(String category){
		ArrayList<String>al=new ArrayList();
		try{
			rs=dbManager.getCategoryBooks(category);
			while(rs.next()){
				al.add(rs.getString(1));
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return al;
	}
	
	public Stock getBook(String book,String author){
		Stock stock=null;
		try{
			rs=dbManager.getStock(book,author);
			if(rs.next())
			stock=new Stock(rs.getInt(1),rs.getInt(2),rs.getInt(5),rs.getString(6));
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return stock;
	}
	
	public void updateStockStatus(int stockId,int val){
		String status=null;
		if(val==1){
			status="available";
		}
		else if(val==2){
			status="reserved";
		}
		else{
			status="notavailable";
		}
		dbManager.updateStockStatus(stockId,status);
	}
	
	public boolean bookIsReserved(int book,int author,int member){
		try{
		if((dbManager.getReservedId(book,author,member)).next()){
			return true;
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return false;
	}
	
	public boolean bookIsReserved(int book,int author){
		try{
		if((dbManager.getReservedId(book,author)).next()){
			return true;
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
		return false;
	}
	
	public int getStockId(int book,int author,int member){
		int id=0;
		try{
		rs=dbManager.getReservedId(book,author,member);
		rs.next();
		id=rs.getInt(1);
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return id;
	}
	
	public int getReservationId(int book,int author,int member){
		int id=0;
		try{
		rs=dbManager.getReservedId(book,author,member);
		rs.next();
		id=rs.getInt(2);
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return id;
	}
	
	public int getReservationId(int book,int author){
		int id=0;
		try{
		rs=dbManager.getReservedId(book,author);
		rs.next();
		id=rs.getInt(2);
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return id;
	}
	
	public void updateReservation(int reservationId,int val){
		String status=null;
		if(val==0){
			status="issued";
		}
		else{
			status="invalid";
		}
		dbManager.updateReservation(reservationId,status);
	}
	
	public List getTransactions(int memberId){
		list=new ArrayList();
		try{
		rs=dbManager.getTransactions(memberId);
		while(rs.next()){
			list.add(new Barrower(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return list;
	}
	
	public List getTransactionHistory(int memberId){
		list=new ArrayList();
		try{
		rs=dbManager.getTransactionHistory(memberId);
		while(rs.next()){
			list.add(new Barrower(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return list;
	}
	
	public boolean isTransactionIdExists(int memberId,int transactionId){
		try{
		rs=dbManager.getTransactions(memberId);
		while(rs.next()){
			if(rs.getInt(1)==transactionId)
				return true;
		}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return false;
	}
	
	public Barrower getTransaction(int transactionId){
		Barrower barrower=null;
		try{
		rs=dbManager.getTransaction(transactionId);
		rs.next();
		barrower=new Barrower(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6));
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return barrower;
	}
	
	public void updateBookLimit(int memberId,int val){
		int count=dbManager.getBookLimit(memberId);	
		if(val==1)
			count+=1;	
		else
			count-=1;	
		dbManager.updateBookLimit(memberId,count);			
	}
	
	public Member getMember(int memberId){
		Member member=null;
		try{
		rs=dbManager.getMember(memberId);
		rs.next();
		member=new Member(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getLong(5),5-(rs.getInt(6)),rs.getInt(6),rs.getInt(7));
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return member;
	}
	
	public ArrayList<Reservation> getReservations(){
		ArrayList<Reservation>al=new ArrayList();
		try{
			rs=dbManager.getReservations();
			while(rs.next()){
				al.add(new Reservation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(6),rs.getString(5),rs.getString(7)));
			}
		}
		catch(SQLException e){
                        e.printStackTrace();
                }
                return al;
		
	}
	
	public void updateReservations(){
		ArrayList<Reservation>al=getReservations();
		String todayDate=date.getCurrentDate();
		int days=0;
		for(Reservation reserve:al){
			days=date.getDays(reserve.getReservationDate(),todayDate);
			if(days>2){
				updateReservation(reserve.getReservationId(),2);
				if(bookIsReserved(getBookId(reserve.getBookName()),getAuthorId(reserve.getAuthorName()))){
					int reservationId=getReservationId(getBookId(reserve.getBookName()),getAuthorId(reserve.getAuthorName()));
					dbManager.updateReservation(reservationId,todayDate,reserve.getStockId());
				}
				else{
					updateStockStatus(reserve.getStockId(),1);
				}
			}
		}
	}
}



























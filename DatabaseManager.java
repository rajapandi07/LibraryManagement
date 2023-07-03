import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DatabaseManager{

	Connection connection=(DatabaseConnection.getInstance()).getConnection();
	
	Statement st=null;
        PreparedStatement ps=null;
	ResultSet rs=null;


	public ResultSet getBookId(String name){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select bookid from book where name='"+name+"'");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int addBook(String name){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into book(name)values('"+name+"')returning bookid;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("bookid");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ResultSet getAuthorId(String name){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select authorid from author where name='"+name+"'");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int addAuthor(String name){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into author(name)values('"+name+"')returning authorid;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("authorid");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ResultSet getCategoryId(String name){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select categoryid from category where name='"+name+"'");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public int addCategory(String name){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into category(name)values('"+name+"')returning categoryid;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("categoryid");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public void addStock(int bookId,int authorId,int categoryId,int year){
		try{
			ps=connection.prepareStatement("insert into bookstock(bookid,authorid,categoryid,published_year)values("+bookId+","+authorId+","+categoryId+",'"+year+"');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateBook(int bookId,int count){
		count+=getBookCount(bookId);
		try{
			ps=connection.prepareStatement("update book set count="+count+"where bookid="+bookId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int getBookCount(int bookId){
		int count=0;
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select count from book where bookid="+bookId+";");
				rs.next();
				count=rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	     return count;
	}
	
	public int addMember(String name,int age,String address,long mobileNo){
		int id=0;
		try{
			ps=connection.prepareStatement("insert into members(name,age,address,contact_no)values('"+name+"',"+age+",'"+address+"',"+mobileNo+")returning memberid;");
				rs=ps.executeQuery();
					rs.next();
					id=rs.getInt("memberid");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ResultSet getBook(String author,String bookName){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select s.stockid,b.name,a.name,c.name,s.published_year from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where b.name='"+bookName+"' and a.name='"+author+"' limit 1;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getAuthors(String bookName){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select a.name from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where b.name='"+bookName+"'group by a.name;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getBook(String author){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select b.name from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where a.name='"+author+"'group by b.name;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getCategoryBook(String bookName){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select s.stockid,b.name,a.name,c.name,s.published_year from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where b.name='"+bookName+"';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getCategoryBooks(String category){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select b.name from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where c.name='"+category+"'group by b.name;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;	
	}
	
	public ResultSet getStock(String book,String author){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select s.stockid,s.bookid,s.authorid,s.categoryid,s.published_year,s.availability from bookstock s join book b on b.bookid=s.bookid join author a on a.authorid=s.authorid join category c on c.categoryid=s.categoryid where b.name='"+book+"' and a.name='"+author+"' and availability='available' limit 1;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public void issueBook(int memberId,int stockId,int bookId,String date){
		try{
			ps=connection.prepareStatement("insert into transactions (bookid,stockid,memberid,borrowed_date)values("+bookId+","+stockId+","+memberId+",'"+date+"');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateStockStatus(int stockId,String status){
		try{
			ps=connection.prepareStatement("update bookstock set availability='"+status+"'where stockid="+stockId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void reserveBook(int bookId,int authorId,int memberId){
		try{
			ps=connection.prepareStatement("insert into reservation (bookid,authorid,memberid)values("+bookId+","+authorId+","+memberId+");");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getReservedId(int book,int author,int member){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select stockid,reservationid from reservation where bookid="+book+" and authorid="+author+" and memberid="+member+" and status='reserved' and reservation_date is not null and stockid is not null;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getReservedId(int book,int author){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select stockid,reservationid from reservation where bookid="+book+" and authorid="+author+" and status='reserved' and reservation_date is null;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public void updateReservation(int reservationId,String status){
		try{
			ps=connection.prepareStatement("update reservation set status='"+status+"'where reservationid="+reservationId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void updateReservation(int reservationId,String date,int stockId){
		try{
			ps=connection.prepareStatement("update reservation set reservation_date='"+date+"',stockid="+stockId+" where reservationid="+reservationId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getTransactions(int memberId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select t.transactionid,b.name,a.name,t.borrowed_date,t.due_date,t.stockid from bookstock s join book b on s.bookid=b.bookid join author a on s.authorid=a.authorid join transactions t on s.stockid=t.stockid where t.memberid="+memberId+" and return_date is null;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getTransactionHistory(int memberId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select t.transactionid,b.name,a.name,t.borrowed_date,t.due_date,t.stockid from bookstock s join book b on s.bookid=b.bookid join author a on s.authorid=a.authorid join transactions t on s.stockid=t.stockid where t.memberid="+memberId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public ResultSet getTransaction(int transactionId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select t.transactionid,b.name,a.name,t.borrowed_date,t.due_date,t.stockid from bookstock s join book b on s.bookid=b.bookid join author a on s.authorid=a.authorid join transactions t on s.stockid=t.stockid where t.transactionid="+transactionId+" and return_date is null;");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public void updatetransactions(int transactionId,String date){
		try{
			ps=connection.prepareStatement("update transactions set return_date='"+date+"'where transactionid="+transactionId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public int getBookLimit(int memberId){
		int count=0;
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select booklimit from members where memberid="+memberId+";");
				rs.next();
		count=rs.getInt(1);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public void updateBookLimit(int memberId,int count){
		try{
			ps=connection.prepareStatement("update members set booklimit="+count+"where memberid="+memberId+";");
			ps.execute();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getMember(int memberId){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select memberid,name,age,address,contact_no,booklimit,(select count(transactionid) from transactions where memberid="+memberId+") from members where memberid="+memberId+";");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
	
	public void chargePenalty(int memberId,int stockId,String reason,int penalty){
		try{
			ps=connection.prepareStatement("insert into penalty (memberid,Stockid,reason,amount,status) values ("+memberId+","+stockId+",'"+reason+"',"+penalty+",'t');");
		ps.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public ResultSet getReservations(){
		try{
			st=connection.createStatement();
				rs=st.executeQuery("select r.reservationid,b.name,a.name,m.name,r.reservation_date,r.stockid,r.status from reservation r join book b on b.bookid=r.bookid join author a on a.authorid=r.authorid join members m on m.memberid=r.memberid where reservation_date is not null and status='reserved';");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	return rs;
	}
}




































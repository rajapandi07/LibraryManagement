public class Reservation{
	
	private int reservationId;
	private String bookName;
	private String authorName;
	private String memberName;
	private int stockId;
	private String reservationDate;
	private String status;
	
	Reservation(int reservationId,String bookName,String authorName,String memberName,int stockId){
		this.reservationId=reservationId;
		this.bookName=bookName;
		this.authorName=authorName;
		this.memberName=memberName;
		this.stockId=stockId;
	}
	
	Reservation(int reservationId,String bookName,String authorName,String memberName,int stockId,String reservationDate,String status){
		this.reservationId=reservationId;
		this.bookName=bookName;
		this.authorName=authorName;
		this.memberName=memberName;
		this.stockId=stockId;
		this.reservationDate=reservationDate;
		this.status=status;
	}
	
	public void setReservationId(int reservationId){
		this.reservationId=reservationId;
	}	
	
	public int getReservationId(){
		return reservationId;
	}
	
	public void setStockId(int stockId){
		this.stockId=stockId;
	}	
	
	public int getStockId(){
		return stockId;
	}
	
	public void setBookName(String bookName){
		this.bookName=bookName;
	}	
	
	public String getBookName(){
		return bookName;
	}
	
	public void setAuthorName(String authorName){
		this.authorName=authorName;
	}	
	
	public String getAuthorName(){
		return authorName;
	}
	
	public void setMemberName(String memberName){
		this.memberName=memberName;
	}	
	
	public String getMemberName(){
		return memberName;
	}
	
	public void setReservationDate(String reservationDate){
		this.reservationDate=reservationDate;
	}	
	
	public String getReservationDate(){
		return reservationDate;
	}
	
	public void setStatus(String status){
		this.status=status;
	}	
	
	public String getStatus(){
		return status;
	}
}

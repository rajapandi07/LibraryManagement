public class Stock{

	private int stockId;
	private int bookId;
	private int year;
	private String availability;
	
	Stock(int stockId,int bookId,int year,String availability){
		this.stockId=stockId;
		this.bookId=bookId;
		this.year=year;
		this.availability=availability;
	}
	
	public void setStockId(int stockId){
		this.stockId=stockId;
	} 
	
	public int getStockId(){
		return stockId;
	}
	
	public void setBookId(int bookId){
		this.bookId=bookId;
	} 
	
	public int getBookId(){
		return bookId;
	}
	
	public void setYear(int year){
		this.year=year;
	} 
	
	public int getYear(){
		return year;
	}
	
	public void setAvailability(String availability){
		this.availability=availability;
	} 
	
	public String getAvailability(){
		return availability;
	}
}

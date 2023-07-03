public class Barrower{

	private int transactionId;
	private String bookName;
	private String authorName;
	private int stockId;
	private String barrowedDate;
	private String dueDate;
	
	Barrower(int transactionId,String bookName,String authorName,String barrowedDate,String dueDate,int stockId){
		this.transactionId=transactionId;
		this.bookName=bookName;
		this.authorName=authorName;
		this.barrowedDate=barrowedDate;
		this.dueDate=dueDate;
		this.stockId=stockId;
	}
	
	public void setTransactionId(int transactionId){
		this.transactionId=transactionId;
	}
	
	public int getTransactionId(){
		return transactionId;
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
	
	public void setBarrowedDate(String barrowedDate){
		this.barrowedDate=barrowedDate;
	}
	
	public String getBarrowedDate(){
		return barrowedDate;
	}
	
	public void setDueDate(String dueDate){
		this.dueDate=dueDate;
	}
	
	public String getDueDate(){
		return dueDate;
	}
	
	public String toString(){
		/*return transactionId+"	|	"+bookName+"	|	"+authorName+"	|	"+barrowedDate+"	|	"+dueDate+"	|\n";
		*/
		 return (String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|",transactionId,bookName,authorName,barrowedDate,dueDate));
		
	}
	
	
}

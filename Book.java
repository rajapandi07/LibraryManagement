public class Book{

	private int bookId;
	private String bookName;
	private String authorName;
	private String categoryName;
	private int year;
	
	Book(int bookId,String bookName,String authorName,String categoryName,int year){
		this.bookId=bookId;
		this.bookName=bookName;
		this.authorName=authorName;
		this.categoryName=categoryName;
		this.year=year;
	}
	
	public void setBookId(int bookId){
		this.bookId=bookId;
	}
	
	public int getBookId(){
		return bookId;
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
	
	public void setCategoryName(String categoryName){
		this.categoryName=categoryName;
	}
	
	public String getCategoryName(){
		return categoryName;
	}
	
	public void setYear(int year){
		this.year=year;
	}
	
	public int getYear(){
		return year;
	}
	
	public String toString(){
		 return String.format("|%-20s|%-35s|%-20s|%-20s|%-20s|",bookId,bookName,authorName,categoryName,year);
	}
}

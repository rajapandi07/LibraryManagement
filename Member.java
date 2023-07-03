public class Member{

	private int memberId;
	private String name;
	private int age;
	private String address;
	private long contactNo;
	private int barrowedBooks;
	private int bookLimit;
	private int totalBarrowedBooks;
	
	Member(int memberId,String name,int age,String address,long contactNo,int barrowedBooks,int bookLimit,int totalBarrowedBooks){
		this.memberId=memberId;
		this.name=name;
		this.age=age;
		this.address=address;
		this.contactNo=contactNo;
		this.barrowedBooks=barrowedBooks;
		this.bookLimit=bookLimit;
		this.totalBarrowedBooks=totalBarrowedBooks;
	}
	
	public void setMemberId(int memberId){
		this.memberId=memberId;
	}
	
	public int getMemberId(){
		return memberId;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAge(int age){
		this.age=age;
	}
	
	public int getAge(){
		return age;
	}
	
	public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setContactNo(long contactNo){
		this.contactNo=contactNo;
	}
	
	public long getContactNo(){
		return contactNo;
	}
	
	public void setBarrowedBooks(int barrowedBooks){
		this.barrowedBooks=barrowedBooks;
	}
	
	public int getBarrowedBooks(){
		return barrowedBooks;
	}
	
	public void setBookLimit(int bookLimit){
		this.bookLimit=bookLimit;
	}
	
	public int getBookLimit(){
		return bookLimit;
	}
	
	public void setTotalBarrowedBooks(int totalBarrowedBooks){
		this.totalBarrowedBooks=totalBarrowedBooks;
	}
	
	public int getTotalBarrowedBooks(){
		return totalBarrowedBooks;
	}
	
	public String toString(){
		return "===================================================================================================\n"+
		       "|                                            Member Details                                       |\n"+
		       "===================================================================================================\n"+
		       "| Id                      |    "+memberId+"\n"+
		       "| Name                    |    "+name+"\n"+
		       "| Age                     |    "+age+"\n"+
		       "| Contact no              |    "+contactNo+"\n"+
		       "| Borrowed Books          |    "+barrowedBooks+"\n"+
		       "| Book Limit              |    "+bookLimit+"\n"+
		       "| Total Borrowed Books    |    "+totalBarrowedBooks+"\n"+
		       "| Address                 |    "+address+"\n"+
		       "===================================================================================================\n";
	}
}

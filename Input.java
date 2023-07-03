import java.util.Scanner;
import java.util.InputMismatchException;  
public class Input{

	Validation validate=new Validation();
	DateValidation date=new DateValidation();
	Scanner scan=new Scanner(System.in);
	
	
	public String getString(String val){
		System.out.print(val);
		String value=scan.nextLine();
	       return value;
	}
	
	public String getName(String val){
		String value=getString(val);
		if(!(validate.nameValidation(value))){
			System.out.println("The given input is not valid.Please re-enter");
			return getName(val);
		}
		return value;
		} 
	
	
	public int getInt(String val){
		System.out.print(val);
		int value=0;
		try{
		value=scan.nextInt();
		}
		catch(InputMismatchException ex){
			System.out.println("!! The given input type is Wrong.Enter input in Numbers..!!");
			scan.nextLine();
			return getInt(val);
		}
	     return value;
	}
	
	
	public long getLong(String val){
		System.out.print(val);
		long value=0;
		try{
		value=scan.nextLong();
		}
		catch(InputMismatchException ex){
			System.out.println("!! The given input type is Wrong.Enter input in Numbers..!!");
			scan.nextLine();
			return getLong(val);
		}
	     return value;
	}
	
	
	public long getPhoneNo(String val){
		long phoneNo=getLong(val);
		if(!(validate.phoneNoValidation(phoneNo))){
			System.out.println("!! The given Number is not valid.re-enter the number");
			return getPhoneNo(val);
		}
		return phoneNo;
	}
	
	
	public String getDate(String val){
		System.out.print(val);
		String value=scan.next();
		if(!(date.isValidDate(value))){
		       System.out.println("!! The given date is invalid.Enter Correct date..!!");
		       return getDate(val);
		}
	         return value;
	}
}

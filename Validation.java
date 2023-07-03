public class Validation{

	
	public boolean nameValidation(String value){
		for(int i=0;i<value.length();i++){
			char letter=value.charAt(i);
			if(!((letter >='a' && letter <='z') || (letter >='A' && letter <='Z') || (letter==' '))){
				return false;
			}
		}
	     return true;
	}
	
	public boolean phoneNoValidation(long phoneNo){
		if(!(phoneNo >6000000000l && phoneNo<=9999999999l)){
			return false;
		}
		return true;
	}
}

package cloudFront.tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmail {
	
	public static void main(String[] args) {
		
		String str = "My email is example@example.com";
		    
		
		     String emailregax = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
		          
		     Pattern pattern = Pattern.compile(emailregax);
		     Matcher matcher = pattern.matcher(str);
		     
		    if(matcher.find())
		    {
		    	String email = matcher.group();
		    	System.out.println("Extracted Email id is :" + email);
		    }
		    else
		    {
		    	System.out.println("Email not found in the input string");
		    }
		
		
	}

}

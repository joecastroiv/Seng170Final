package WindowBuilder.common;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


import java.io.IOException;

public class LoginChecker {
	

	public static boolean isMatchLogin(String Login) throws IOException
	{
		FileInputStream confidentialLogins = null; //file input stream
		Scanner inFS = null;                     //Scanner object    
		
		
		// Try to open file
		System.out.println("Opening file confidentialLogins.txt.");	
		confidentialLogins = new FileInputStream("docs\\confidentialLogins.txt");
		inFS = new Scanner(confidentialLogins);
		
		// File is open and valid if we got this far (otherwise exception thrown)
		System.out.println("Reading and printing logins.");
		
		while (inFS.hasNext()) {
			String user = inFS.next();
			
			if(Login.equals(user)) {
					System.out.println("Welcome: You Are Logged In");
					inFS.close();
					return true; 
				}											    
				
			}
							
			System.out.println("Login Invalid");
			inFS.close();
		    return false; 
		   
	}
	
	 public static String isMatch(String errorCode)  { 
	    	
	    	FileInputStream errorCodes = null; //file input stream
			Scanner inFS = null;                     //Scanner object               
			
	        try {
	        	errorCodes = new FileInputStream("docs\\errorCodes.txt");
				
				inFS = new Scanner(errorCodes);
				
				if (inFS.hasNext() != true) {
					throw new Exception("Empty file");
				}
				
				
				while (inFS.hasNext()) {
				String oneLine = inFS.nextLine();
				String[] arrInfo = oneLine.split(":");
							
				if(errorCode.equals(arrInfo[0])) {
	
						return arrInfo[1]; 
					}
					
								
				}
				inFS.close();
			    return "error not found"; 
				
			} catch (FileNotFoundException e) {
							
				System.out.println("File not found " + e.getMessage());		
			
				return "file not found";
				//e.printStackTrace();
			} catch (Exception e1) {
				System.out.println("File is empty " + e1.getMessage());
				return "file not found";
			}

	}
}

package edu.hm;

import edu.hm.cs.swa.projekt_2.datamodel.Token;
import edu.hm.cs.swa.projekt_2.logic.AuthorizationIDEnum;
import edu.hm.cs.swa.projekt_2.logic.ValidationResult;
import edu.hm.cs.swa.projekt_2.logic.ValidationService;

public class DemoApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Token token = ValidationService.INSTANCE.getToken("user1","asd");
		
		System.out.println("Token: "+token.getID());
		
		ValidationResult valResult = ValidationService.INSTANCE.validateToken(token, AuthorizationIDEnum.BOOK_READ);
		
		System.out.println(valResult);
	}

}

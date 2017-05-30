package edu.hm.cs.swa.projekt_2.logic;

public enum AuthorizationIDEnum {

	
	
	BOOK_READ("book.read"),
	BOOK_WRITE("book.write");
	
	private final String authorizationID;
	
	AuthorizationIDEnum(String authorizationID){
		this.authorizationID = authorizationID;
	}

	public String getAuthorizationID() {
		return authorizationID;
	}
	
	
}

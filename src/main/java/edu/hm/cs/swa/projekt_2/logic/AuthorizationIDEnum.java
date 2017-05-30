package edu.hm.cs.swa.projekt_2.logic;

public enum AuthorizationIDEnum {

	
	
	BOOK_READ("media.book.get"),
	BOOK_WRITE("media.book.update");
	
	private final String authorizationID;
	
	AuthorizationIDEnum(String authorizationID){
		this.authorizationID = authorizationID;
	}

	public String getAuthorizationID() {
		return authorizationID;
	}
	
	
}

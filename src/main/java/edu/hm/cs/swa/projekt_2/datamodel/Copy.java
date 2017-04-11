package edu.hm.cs.swa.projekt_2.datamodel;

public class Copy {

	private Medium medium;
	private String owner;
	
	public Copy(Medium medium, String owner){
		this.medium = medium;
		this.owner = owner;
	}

	public Medium getMedium() {
		return medium;
	}

	public String getOwner() {
		return owner;
	}
	
	
	
}

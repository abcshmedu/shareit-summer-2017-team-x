package edu.hm.cs.swa.projekt_2.datamodel;

/**
 * Representing a copy of a medium, that is used by a customer.
 */
public class Copy {

	private Medium medium;
	private String owner;
	
	/**
	 * Parameterized constructor. 
	 * 
	 * @param medium
	 * @param owner
	 */
	public Copy(Medium medium, String owner){
		this.medium = medium;
		this.owner = owner;
	}

	/**
	 * Returns the medium
	 * 
	 * @return Medium
	 */
	public Medium getMedium() {
		return medium;
	}

	/**
	 * Returns the owner. This can be null
	 * 
	 * @return String
	 */
	public String getOwner() {
		return owner;
	}
	
	
	
}

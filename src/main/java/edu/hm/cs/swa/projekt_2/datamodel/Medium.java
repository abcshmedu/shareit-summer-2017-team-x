package edu.hm.cs.swa.projekt_2.datamodel;

public abstract class Medium {

	private String title;
	
	public Medium(String title){
		this.title = title;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public String getTitle(){
		return this.title;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}

package edu.hm.cs.swa.projekt_2.datamodel;

public class Disc extends Medium {

	
	private String barcode;
	private String director;
	private int fsk;
	
	
	public Disc(){
		super("");
	}
	
	public Disc(String barcode, String director, int fsk,String title) {
		super(title);
		
		this.barcode = barcode;
		this.director = director;
		this.fsk = fsk;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
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

	public String getBarcode() {
		return barcode;
	}

	public String getDirector() {
		return director;
	}

	public int getFsk() {
		return fsk;
	}
	
	
	


}

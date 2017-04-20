package edu.hm.cs.swa.projekt_2.datamodel;

public class Disc extends Medium {


    private String barcode;
    private String director;
    private int fsk;


    public Disc() {
        super("");
    }

    public Disc(String barcode, String director, int fsk, String title) {
        super(title);

        this.barcode = barcode;
        this.director = director;
        this.fsk = fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disc)) return false;
        if (!super.equals(o)) return false;

        Disc disc = (Disc) o;

        if (getFsk() != disc.getFsk()) return false;
        if (!getBarcode().equals(disc.getBarcode())) return false;
        return getDirector().equals(disc.getDirector());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getBarcode().hashCode();
        result = 31 * result + getDirector().hashCode();
        result = 31 * result + getFsk();
        return result;
    }

    @Override
    public String toString() {
        return "Disc{" + super.toString() +
                ", barcode='" + barcode + '\'' +
                ", director='" + director + '\'' +
                ", fsk=" + fsk +
                '}';
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

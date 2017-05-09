package edu.hm.cs.swa.projekt_2.datamodel;

/**
 * Representing a Disc, derived from {@linkplain Medium}
 */
public class Disc extends Medium {

    private String barcode;
    private String director;
    private Integer fsk;

    /**
     * Default constructor for serialization
     */
    Disc() {
    }

    /**
     * Parameterized constructor for creating a new element
     *
     * @param barcode
     * @param director
     * @param fsk
     * @param title
     */
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

    /**
     * Returns the barcode, this can be null.
     *
     * @return
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Sets the Barcode.
     *
     * @param barcode String of the new Barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * Returns the director, this can be null
     *
     * @return
     */
    public String getDirector() {
        return director;
    }

    /**
     * Returns the FSK. If not set, it returns 0
     *
     * @return
     */
    public Integer getFsk() {
        return fsk;
    }

    /**
     * Set the director
     *
     * @param director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Set the FSK
     *
     * @param fsk
     */
    public void setFsk(int fsk) {
        this.fsk = fsk;
    }
}

package edu.hm.cs.swa.projekt_2.datamodel;

/**
 * Abstract superclass to bundle common informations
 *
 */
public abstract class Medium {

    private String title;

    /**
     * Default constructor for serialization
     */
    Medium() {
    }

    /**
     * Parameterized constructor for creating a new element
     * @param title
     */
    public Medium(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medium)) return false;

        Medium medium = (Medium) o;

        return getTitle().equals(medium.getTitle());
    }

    @Override
    public int hashCode() {
        return getTitle().hashCode();
    }

    @Override
    public String toString() {
        return "Medium{" +
                "title='" + title + '\'' +
                '}';
    }

    /**
     * Returns the title, this can be null;
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title.
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }
}

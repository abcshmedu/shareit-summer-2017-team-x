package edu.hm.cs.swa.projekt_2.datamodel;

public abstract class Medium {

    private String title ="";

    Medium() {
    }

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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

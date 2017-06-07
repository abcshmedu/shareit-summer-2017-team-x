package edu.hm.cs.swa.projekt_2.datamodel;

import java.io.Serializable;

/**
 * Representing a copy of a medium, that is used by a customer.
 */
//@Entity
public class Copy implements Serializable {

    //@Id
    //@Column
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCopy;

    //@ManyToOne
    //@JoinColumn(name = "fk_medium")
    private Medium medium;

    //@Column
    private String copyOwner;

    /**
     * Parameterized constructor.
     *
     * @param medium
     * @param copyOwner
     */
    public Copy(Medium medium, String copyOwner) {
        this.medium = medium;
        this.copyOwner = copyOwner;
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
     * Returns the copyOwner. This can be null
     *
     * @return String
     */
    public String getCopyOwner() {
        return copyOwner;
    }


    public long getIdCopy() {
        return idCopy;
    }
}

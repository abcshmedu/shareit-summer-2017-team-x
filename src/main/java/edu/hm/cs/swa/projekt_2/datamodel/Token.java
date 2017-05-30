package edu.hm.cs.swa.projekt_2.datamodel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Token {


    private String ID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    private Date valideUntil;

    public Token() {

    }


    public Token(String iD, Date created, Date valideUntil) {
        super();
        ID = iD;
        this.created = created;
        this.valideUntil = valideUntil;
    }


    public String getID() {
        return ID;
    }

    public Date getCreated() {
        return created;
    }

    public Date getValideUntil() {
        return valideUntil;
    }

    public boolean isValid(Date checkDate) {
        return checkDate.after(getCreated()) && checkDate.before(getValideUntil());
    }

    @Override
    public String toString() {
        return "Token{" +
                "ID='" + ID + '\'' +
                ", created=" + created +
                ", valideUntil=" + valideUntil +
                '}';
    }
}

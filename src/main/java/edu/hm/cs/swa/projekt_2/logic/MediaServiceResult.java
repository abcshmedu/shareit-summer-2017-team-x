package edu.hm.cs.swa.projekt_2.logic;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.ws.rs.core.Response;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MediaServiceResult {

    OK(200, Response.Status.OK, "Request erfolgreich!"),
    CREATED(201, Response.Status.CREATED, "Ressource wurde erstellt!"),
    MISSING_CONTENT(400, Response.Status.BAD_REQUEST, "Notwendige Daten wurden nicht mit angegeben!"),
    NOT_FOUND(404, Response.Status.NOT_FOUND, "Ressource konnte nicht gefunden werden"),
    ISBN_INVALID(400, Response.Status.BAD_REQUEST, "ISBN ungültig!"),
    ISBN_IMMUTABLE(400, Response.Status.BAD_REQUEST, "ISBN kann nicht verändert werden!"),
    BARCODE_INVALID(400, Response.Status.BAD_REQUEST, "Barcode ungültig!"),
    BARCODE_IMMUTABLE(400, Response.Status.BAD_REQUEST, "Barcode kann nicht verändert werden!"),
    ALREADY_EXISTS(400, Response.Status.BAD_REQUEST, "Ressource existiert bereits!"),
    MISSING_TITLE(400, Response.Status.BAD_REQUEST, "Titel fehlt!"),
    MISSING_AUTHOR(400, Response.Status.BAD_REQUEST, "Autor fehlt!"),
    MISSING_DIRECTOR(400, Response.Status.BAD_REQUEST, "Director fehlt!"),
    MISSING_FSK(400, Response.Status.BAD_REQUEST, "FSK fehlt!");

    private final int code;
    private final Response.Status status;
    private final String additionalMsg;


    MediaServiceResult(int code, Response.Status status, String msg) {
        this.code = code;
        this.status = status;
        this.additionalMsg = msg;
    }

    public int getCode() {
        return code;
    }

    public Response.Status getStatus() {
        return status;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }


}

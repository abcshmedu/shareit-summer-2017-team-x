package edu.hm.cs.swa.projekt_2.logic;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.ws.rs.core.Response;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ValidationResult {
    CREATE_USER(201, Response.Status.CREATED, "Benutzer erstellt!"),
    CREATE_USER_FAILED(400, Response.Status.BAD_REQUEST, "Benutzer erstellen fehlgeschlagen!"),
    AUTHORIZATION_OK(200, Response.Status.OK, "Authorisierung erfolgreich!"),
    AUTHORIZATION_MISSING(403, Response.Status.FORBIDDEN, "Authorisierung fehlgeschlagen!"),
    TOKEN_EXPRIED(410, Response.Status.GONE, "Token ausgelaufen!"),
    TOKEN_INVALIDE(404, Response.Status.NOT_FOUND, "Token invalide!");

    private final int code;
    private final Response.Status status;
    private final String additionalMsg;

    /**
     * Creates a new ValidationResult. this contains of a code, a status and a message.
     *
     * @param code
     * @param status
     * @param msg
     */
    ValidationResult(int code, Response.Status status, String msg) {
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

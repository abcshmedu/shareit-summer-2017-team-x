package edu.hm.cs.swa.projekt_2.logic;

import javax.ws.rs.core.Response;

public enum MediaServiceResult {

    OK(200, Response.Status.OK), NOT_FOUND(404, Response.Status.NOT_FOUND);

    private final int code;
    private final Response.Status status;

    private MediaServiceResult(int code, Response.Status status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public Response.Status getStatus() {
        return status;
    }
}

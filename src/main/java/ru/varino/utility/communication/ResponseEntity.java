package ru.varino.utility.communication;


public class ResponseEntity {
    private final int exitCode;
    private final String metaName;
    private String body;

    public ResponseEntity(Status status) {
        this.exitCode = status.getExitCode();
        this.metaName = status.getMetaName();
    }

    public ResponseEntity body(String body) {
        this.body = body;
        return this;
    }

    public String getBody() {
        return body;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getMetaName() {
        return metaName;
    }

    public static ResponseEntity ok() {
        return new ResponseEntity(Status.OK);
    }

    public static ResponseEntity badRequest() {
        return new ResponseEntity(Status.BADREQUEST);
    }

    public static ResponseEntity notFound() {
        return new ResponseEntity(Status.NOTFOUND);
    }

    public static ResponseEntity serverError() {
        return new ResponseEntity(Status.SERVERERROR);
    }

    public static ResponseEntity exit(){
        return new ResponseEntity(Status.SERVERERROR);
    }
}

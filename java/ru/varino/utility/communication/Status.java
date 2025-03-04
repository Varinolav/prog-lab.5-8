package ru.varino.utility.communication;

public enum Status {
    OK(200, "OK"),
    BADREQUEST(400, "Bad Request"),
    NOTFOUND(404, "Not found"),
    SERVERERROR(500, "Internal server error");
    int exitCode;
    String metaName;

    Status(int exitCode, String metaName) {
        this.exitCode = exitCode;
        this.metaName = metaName;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getMetaName() {
        return metaName;
    }
}

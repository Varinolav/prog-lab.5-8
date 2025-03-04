package ru.varino.utility.communication;

public class RequestEntity {
    private String command;
    private String params;
    private Object body;

    public RequestEntity(String command, String params, Object data) {

        this.command = command;
        this.params = params;
        this.body = data;
    }

    public RequestEntity(String command, String params) {
        this.command = command;
        this.params = params;
    }

    public RequestEntity body(Object data) {
        this.body = data;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public String getParams() {
        return params;
    }

    public static RequestEntity create(String command, String params) {
        return new RequestEntity(command, params);
}
}

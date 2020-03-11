package org.kalin.sanity_check;

public class ClientException extends Exception {
    private String message;
    private int statusCode;

    public ClientException(String message, int statusCode) {
        super(message);
        this.message = message;
        this.statusCode = statusCode;
    }

    public ClientException() {
        super("unknown exception");
        this.message = "unknown";
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}


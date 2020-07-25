package com.stackfortech.demohateoas.response;

public class ErrorResponse {

    private String message;
    private Object object;
    private int status;

    public ErrorResponse(String message, Object object, int status) {
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public ErrorResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", object=" + object +
                ", status=" + status +
                '}';
    }
}

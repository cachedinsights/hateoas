package com.stackfortech.demohateoas.response;

public class SuccessResponse {

    private String message;
    private Object object;
    private int status;

    public SuccessResponse(String message, Object object, int status) {
        this.message = message;
        this.object = object;
        this.status = status;
    }

    public SuccessResponse() {
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
        return "SuccessResponse{" +
                "message='" + message + '\'' +
                ", object=" + object +
                ", status=" + status +
                '}';
    }
}

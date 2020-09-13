package org.example.process.control.exceptions;

public class EmailException extends Exception {

    private String reason;

    public EmailException(String reason){
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}

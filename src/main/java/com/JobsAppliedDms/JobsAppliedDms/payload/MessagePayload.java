package com.JobsAppliedDms.JobsAppliedDms.payload;

/* Message Payload
*
* JSON Payload meant only to deliver a message
* */


public class MessagePayload
{
    private String message;

    public MessagePayload() {}

    public MessagePayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

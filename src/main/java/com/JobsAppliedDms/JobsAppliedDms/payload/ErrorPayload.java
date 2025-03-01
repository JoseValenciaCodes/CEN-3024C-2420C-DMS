package com.JobsAppliedDms.JobsAppliedDms.payload;

/* Error Payload
*
* JSON Payload to throw when errors happen
* */

public class ErrorPayload
{
    private String error;

    public ErrorPayload(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

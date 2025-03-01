package com.JobsAppliedDms.JobsAppliedDms.exception;

/*
* IncorrectCredentials Exception
* Throw when the user password is incorrect
* */

public class IncorrectCredentials extends RuntimeException
{
    public IncorrectCredentials(String message)
    {
        super(message);
    }
}

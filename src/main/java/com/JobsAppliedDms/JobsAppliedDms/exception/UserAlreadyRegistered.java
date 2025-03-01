package com.JobsAppliedDms.JobsAppliedDms.exception;

/*
* Throw when trying to register a user that is already registered
* */

public class UserAlreadyRegistered extends RuntimeException
{
    public  UserAlreadyRegistered(String message)
    {
        super(message);
    }
}

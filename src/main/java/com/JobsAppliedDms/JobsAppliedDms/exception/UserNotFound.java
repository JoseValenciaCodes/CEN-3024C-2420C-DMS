package com.JobsAppliedDms.JobsAppliedDms.exception;

/*
* UserNotFound Exception
* Throw when a user is requested but does not exist in the system
* */

import com.JobsAppliedDms.JobsAppliedDms.entity.User;

public class UserNotFound extends RuntimeException
{
    public UserNotFound(String message)
    {
        super(message);
    }
}

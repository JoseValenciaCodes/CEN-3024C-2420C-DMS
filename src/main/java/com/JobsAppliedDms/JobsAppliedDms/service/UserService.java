package com.JobsAppliedDms.JobsAppliedDms.service;

/* User Service Interface
* Define all ways to interact with the User entity here
*
* */

import com.JobsAppliedDms.JobsAppliedDms.dto.LoginDto;
import com.JobsAppliedDms.JobsAppliedDms.dto.UserDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.User;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.UserPayload;
import jakarta.servlet.http.HttpSession;

public interface UserService
{
    UserPayload registerUser(UserDto userDto, HttpSession httpSession);
    UserPayload loginUser(LoginDto loginDto, HttpSession httpSession);
    User getLoggedInUser(HttpSession httpSession);
    MessagePayload logout(HttpSession httpSession);
}

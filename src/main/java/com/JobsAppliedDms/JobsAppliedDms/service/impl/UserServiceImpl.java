package com.JobsAppliedDms.JobsAppliedDms.service.impl;

/* User Service Implementation
*
* Implement all business logic required to interact with the User entity
* */

import com.JobsAppliedDms.JobsAppliedDms.dto.LoginDto;
import com.JobsAppliedDms.JobsAppliedDms.dto.UserDto;
import com.JobsAppliedDms.JobsAppliedDms.entity.User;
import com.JobsAppliedDms.JobsAppliedDms.exception.IncorrectCredentials;
import com.JobsAppliedDms.JobsAppliedDms.exception.UserAlreadyRegistered;
import com.JobsAppliedDms.JobsAppliedDms.exception.UserNotFound;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.UserPayload;
import com.JobsAppliedDms.JobsAppliedDms.repository.UserRepository;
import com.JobsAppliedDms.JobsAppliedDms.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
    // Attributes
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register User
    @Override
    public UserPayload registerUser(UserDto userDto, HttpSession httpSession)
    {
        // If user exists, throw an exception
        if (userRepository.existsByEmail(userDto.getEmail()))
        {
            throw new UserAlreadyRegistered("User of email: " + userDto.getEmail() + " is already registered in the system");
        }

        // In case the user isn't register, register the user
        User user = new User();

        // Set attributes manually bc of password hashing
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAge(userDto.getAge());
        user.setIsEmployed(userDto.getIsEmployed());

        // Return and save in the DB the newly registered user
        User newUser = userRepository.save(user);

        // Store session info
        httpSession.setAttribute("userId", newUser.getId());

        // Return the appropriate user payload
        return new UserPayload(
                newUser.getId(),
                newUser.getFirstName(),
                newUser.getLastName(),
                newUser.getEmail(),
                newUser.getAge(),
                newUser.getIsEmployed(),
                newUser.getCreatedAt()
        );
    }
    @Override
    public UserPayload loginUser(LoginDto loginDto, HttpSession httpSession)
    {
        // Try to find the user by email
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());

        // If there is no user, return error
        if (userOptional.isEmpty())
        {
            throw new UserNotFound("User of email: " + loginDto.getEmail() + " could not be found");
        }

        // If the user is found, get a reference to it
        User user = userOptional.get();

        // Check if the password in the DB matches the given password
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
        {
            throw new IncorrectCredentials("Incorrect credentials");
        }

        // Set user in HttpSession
        httpSession.setAttribute("userId", user.getId());

        // Return a payload of the user
        UserPayload userPayload = new UserPayload();

        userPayload.setId(user.getId());
        userPayload.setFirstName(user.getFirstName());
        userPayload.setLastName(user.getLastName());
        userPayload.setEmail(user.getEmail());
        userPayload.setAge(user.getAge());
        userPayload.setIsEmployed(user.getIsEmployed());
        userPayload.setCreatedAt(user.getCreatedAt());

        return userPayload;
    }

    @Override
    public MessagePayload logout(HttpSession httpSession)
    {
        // Invalidate the session
        httpSession.invalidate();

        return new MessagePayload("You have successfully logged out");
    }

    @Override
    public User getLoggedInUser(HttpSession httpSession)
    {
        // Get the currently registered user
        Long userId = (Long) httpSession.getAttribute("userId");

        return userId == null ? null : userRepository.findById(userId).orElse(null);
    }
}

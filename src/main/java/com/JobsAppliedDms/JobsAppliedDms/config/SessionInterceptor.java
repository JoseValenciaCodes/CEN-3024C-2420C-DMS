package com.JobsAppliedDms.JobsAppliedDms.config;

/* Session Interceptor
* Check if there is a valid user session before accessing certain API endpoints
* */

import com.JobsAppliedDms.JobsAppliedDms.payload.ErrorPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class SessionInterceptor implements HandlerInterceptor
{
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        // Try to get the existing session
        HttpSession session = request.getSession(false);

        // Session is invalid, so return 401 Unauthorized response
        if (session == null || session.getAttribute("userId") == null)
        {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
            response.setContentType("application/json"); // Define we'll return a JSON payload
            response.setCharacterEncoding("UTF-8");

            // Create JSON response
            ErrorPayload errorPayload = new ErrorPayload("Unauthorized Access. Please log in");
            response.getWriter().write(objectMapper.writeValueAsString(errorPayload));
            return false;
        }

        return true;
    }
}

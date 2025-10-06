package com.kennet.Expense.Manager.utils;

import com.kennet.Expense.Manager.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper to build a standard authentication success response payload.
 */
public class AuthResponseBuilder {

    public static Map<String, Object> buildAuthResponse(String token, User user){
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", buildUserResponse(user));
        return response;
    }

    private static Map<String, Object> buildUserResponse(User user){
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("id", user.getId());
        userMap.put("email", user.getEmail());
        userMap.put("role", user.getRole().name());
        userMap.put("name", user.getName());
        userMap.put("lastname", user.getLastname());
        return userMap;
    }

}

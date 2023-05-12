package com.niziolekp.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}

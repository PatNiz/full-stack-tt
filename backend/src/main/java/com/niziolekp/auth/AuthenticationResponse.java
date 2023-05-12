package com.niziolekp.auth;

import com.niziolekp.customer.CustomerDTO;

public record AuthenticationResponse (
        String token,
        CustomerDTO customerDTO){
}

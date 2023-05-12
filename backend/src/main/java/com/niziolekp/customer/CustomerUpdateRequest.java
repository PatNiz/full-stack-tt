package com.niziolekp.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}

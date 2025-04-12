package com.kata.customer.dto;


import java.util.List;

public record CustomerDTO(
        Long id,
        String fullName,
        String email,
        List<OrderDTO> orders
) {}


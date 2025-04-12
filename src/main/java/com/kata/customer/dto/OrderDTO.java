package com.kata.customer.dto;


import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        LocalDateTime orderDate,
        List<OrderProductDTO> products
) {}


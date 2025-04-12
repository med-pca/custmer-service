package com.kata.customer.dto;


public record RefundDTO(
        Long id,
        String reason,
        String imageUrl,
        String status,
        Long orderProductId
) {}





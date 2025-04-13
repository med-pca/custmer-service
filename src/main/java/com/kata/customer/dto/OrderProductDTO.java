package com.kata.customer.dto;

public record OrderProductDTO(
        Long id,
        String productName,
        double price,
        RefundDTO refundRequest
) {}

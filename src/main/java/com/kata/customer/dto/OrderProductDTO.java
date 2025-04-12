package com.kata.customer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record OrderProductDTO(
        Long id,
        String productName,
        double price,
        RefundDTO refundRequest
) {}

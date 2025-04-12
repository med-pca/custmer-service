package com.kata.customer.service;

import com.kata.customer.dto.RefundDTO;
import com.kata.customer.exception.ResourceNotFoundException;
import com.kata.customer.mapper.RefundRequestMapper;
import com.kata.customer.model.Customer;
import com.kata.customer.model.Order;
import com.kata.customer.model.OrderProduct;
import com.kata.customer.model.RefundRequest;
import com.kata.customer.repository.OrderProductRepository;
import com.kata.customer.repository.RefundRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RefundServiceTest {

    private RefundRequestRepository refundRequestRepository;
    private OrderProductRepository orderProductRepository;
    private RefundRequestMapper refundRequestMapper;
    private RefundService refundService;

    @BeforeEach
    void setup() {
        refundRequestRepository = mock(RefundRequestRepository.class);
        orderProductRepository = mock(OrderProductRepository.class);
        refundRequestMapper = mock(RefundRequestMapper.class);
        refundService = new RefundService(refundRequestRepository, orderProductRepository, refundRequestMapper);
    }

    @Test
    void submitRefund_shouldCreateRefund_WhenCustomerOwnsProduct() {

        Long customerId = 1L;
        Long productId = 100L;
        RefundDTO inputDTO = new RefundDTO(null, "Broken", "url", null, productId);

        Customer customer = new Customer(); customer.setId(customerId);
        Order order = new Order(); order.setCustomer(customer);
        OrderProduct orderProduct = new OrderProduct(); orderProduct.setId(productId); orderProduct.setOrder(order);

        RefundRequest entity = new RefundRequest();
        RefundRequest saved = new RefundRequest(); saved.setId(10L);
        RefundDTO expectedDTO = new RefundDTO(10L, "Broken", "url", "PENDING", productId);

        when(orderProductRepository.findById(productId)).thenReturn(Optional.of(orderProduct));
        when(refundRequestMapper.toEntity(inputDTO)).thenReturn(entity);
        when(refundRequestRepository.save(entity)).thenReturn(saved);
        when(refundRequestMapper.toDTO(saved)).thenReturn(expectedDTO);

        RefundDTO result = refundService.submitRefund(customerId, inputDTO);

        assertEquals(expectedDTO, result);
        verify(refundRequestRepository).save(entity);
    }

    @Test
    void submitRefund_shouldThrowIfOrderProductNotFound() {
        Long customerId = 1L;
        Long productId = 999L;
        RefundDTO refundDTO = new RefundDTO(null, "Broken", "url", null, productId);

        when(orderProductRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> refundService.submitRefund(customerId, refundDTO));
    }

    @Test
    void submitRefund_shouldThrowIfProductNotBelongToCustomer() {

        Long customerId = 1L;
        Long productId = 123L;
        RefundDTO refundDTO = new RefundDTO(null, "Raison", "url", null, productId);

        Customer otherCustomer = new Customer(); otherCustomer.setId(2L); // ≠ customerId
        Order order = new Order(); order.setCustomer(otherCustomer);
        OrderProduct orderProduct = new OrderProduct(); orderProduct.setId(productId); orderProduct.setOrder(order);

        when(orderProductRepository.findById(productId)).thenReturn(Optional.of(orderProduct));

        assertThrows(ResourceNotFoundException.class, () -> refundService.submitRefund(customerId, refundDTO));
    }
}

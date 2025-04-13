package com.kata.customer.service;


import com.kata.customer.dto.CustomerDTO;
import com.kata.customer.dto.OrderDTO;
import com.kata.customer.exception.ResourceNotFoundException;
import com.kata.customer.mapper.CustomerMapper;
import com.kata.customer.model.Customer;
import com.kata.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private CustomerService customerService;

    @BeforeEach
    void setup() {
        customerRepository = mock(CustomerRepository.class);
        customerMapper = mock(CustomerMapper.class);
        customerService = new CustomerService(customerRepository, customerMapper);
    }

    @Test
    void getListProductByCustomerId_shouldReturnOrders() {
        Long id = 1L;
        Customer customer = new Customer(); customer.setId(id);
        CustomerDTO customerDTO = new CustomerDTO(id, "Mohammed Lyazidi", "mohammed@mail.com", List.of());

        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        when(customerMapper.toDTO(customer)).thenReturn(customerDTO);

        List<OrderDTO> result = customerService.getListProductByCustomerId(id);
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getListProductByCustomerId_shouldThrowNotFound() {
        Long id = 99L;
        when(customerRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> customerService.getListProductByCustomerId(id));
    }
}


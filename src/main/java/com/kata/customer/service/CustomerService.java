package com.kata.customer.service;

import com.kata.customer.dto.CustomerDTO;
import com.kata.customer.dto.OrderDTO;
import com.kata.customer.exception.ResourceNotFoundException;
import com.kata.customer.model.Customer;
import com.kata.customer.mapper.CustomerMapper;
import com.kata.customer.mapper.OrderMapper;
import com.kata.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<OrderDTO> getListProductByCustomerId(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));


        CustomerDTO customerDTO = customerMapper.toDTO(customer);

        return customerDTO.orders();
    }


}

package com.kata.customer.controller;


import com.kata.customer.dto.OrderDTO;
import com.kata.customer.dto.RefundDTO;
import com.kata.customer.service.CustomerService;
import com.kata.customer.service.RefundService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final RefundService refundService;

    public CustomerController(CustomerService customerService, RefundService refundService) {
        this.customerService = customerService;
        this.refundService = refundService;
    }


    @GetMapping("/{id}/orders")
    public List<OrderDTO> getListOrderByCustomerId(@PathVariable Long id) {
        return customerService.getListProductByCustomerId(id);
    }

    @PostMapping("/{id}/refund")
    public RefundDTO submitRefund(@PathVariable Long id, @RequestBody RefundDTO refundRequestDTO) {
        return refundService.submitRefund(id, refundRequestDTO);
    }



}

package com.kata.customer.service;


import com.kata.customer.dto.RefundDTO;
import com.kata.customer.exception.ResourceNotFoundException;
import com.kata.customer.model.OrderProduct;
import com.kata.customer.model.RefundRequest;
import com.kata.customer.mapper.RefundRequestMapper;
import com.kata.customer.repository.OrderProductRepository;
import com.kata.customer.repository.RefundRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RefundService {

    private final RefundRequestRepository refundRequestRepository;
    private final OrderProductRepository orderProductRepository;
    private final RefundRequestMapper refundRequestMapper;

    public RefundService(RefundRequestRepository refundRequestRepository,
                         OrderProductRepository orderProductRepository, RefundRequestMapper refundRequestMapper) {
        this.refundRequestRepository = refundRequestRepository;
        this.orderProductRepository = orderProductRepository;
        this.refundRequestMapper = refundRequestMapper;
    }


    public RefundDTO submitRefund(Long customerId, RefundDTO refundDTO) {
        Long orderProductId =  refundDTO.orderProductId();


        OrderProduct orderProduct = orderProductRepository.findById(orderProductId)
                .orElseThrow(() -> new ResourceNotFoundException("OrderProduct not found with id: " + orderProductId));

        Long actualCustomerId = orderProduct.getOrder().getCustomer().getId();

        if (!actualCustomerId.equals(customerId)) {
            throw new ResourceNotFoundException("OrderProduct with id " + orderProductId + " does not belong to customer with id " + customerId);
        }

        RefundRequest refundRequest = refundRequestMapper.toEntity(refundDTO);
        refundRequest.setOrderProduct(orderProduct);
        refundRequest.setStatus("PENDING");

        RefundRequest saved = refundRequestRepository.save(refundRequest);
        return refundRequestMapper.toDTO(saved);
    }

}


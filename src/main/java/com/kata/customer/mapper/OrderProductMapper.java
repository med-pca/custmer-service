package com.kata.customer.mapper;


import com.kata.customer.dto.OrderProductDTO;
import com.kata.customer.model.OrderProduct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderProductMapper {
    OrderProductDTO toDTO(OrderProduct orderProduct);
    OrderProduct toEntity(OrderProductDTO orderProductDTO);
}


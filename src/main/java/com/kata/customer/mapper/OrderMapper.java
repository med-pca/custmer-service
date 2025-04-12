package com.kata.customer.mapper;

import com.kata.customer.dto.OrderDTO;
import com.kata.customer.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}

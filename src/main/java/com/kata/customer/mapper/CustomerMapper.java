package com.kata.customer.mapper;

import com.kata.customer.dto.CustomerDTO;
import com.kata.customer.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
@Component
public interface CustomerMapper {


    CustomerDTO toDTO(Customer customer);
    Customer toEntity(CustomerDTO customerDTO);
}

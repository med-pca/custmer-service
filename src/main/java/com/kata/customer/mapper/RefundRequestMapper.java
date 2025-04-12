package com.kata.customer.mapper;

import com.kata.customer.dto.RefundDTO;
import com.kata.customer.model.RefundRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {OrderProductMapper.class})
@Component
public interface RefundRequestMapper {
    @Mapping(source = "orderProduct.id", target = "orderProductId")
    RefundDTO toDTO(RefundRequest refundRequest);

    @Mapping(target = "orderProduct", ignore = true) // on le gère dans le service
    RefundRequest toEntity(RefundDTO refundDTO);
}

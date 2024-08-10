package com.geeth.karma_test_backend.domain.dto.convertor;

import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.SaleDto;
import com.geeth.karma_test_backend.external.entity.PaymentStatus;
import com.geeth.karma_test_backend.external.entity.Sale;
import com.geeth.karma_test_backend.external.entity.Status;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    private final ModelMapper modelMapper;

    public SaleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Sale convertToEntity(SaleCreateRequest saleCreateRequest) {

        Date date = new Date();
        Sale sale = modelMapper.map(saleCreateRequest, Sale.class);
        sale.setCreatedAt(date);
        sale.setUpdatedAt(date);
        sale.setUpdatedBy(sale.getBiller());
        sale.setActive(1);
        sale.setCreatedBy(saleCreateRequest.getBiller());
        return setDueAndStatus(sale);
    }

    public SaleDto convertToDto(Sale sale) {
        return modelMapper.map(sale, SaleDto.class);
    }

    public List<SaleDto> convertToDtoList(List<Sale> saleList) {
        return saleList.stream()
                .map(sale -> {
                    SaleDto saleDto = modelMapper.map(sale, SaleDto.class);
                    return saleDto;
                })
                .collect(Collectors.toList());
    }

    public Sale convertUpdateRequestToEntity(SaleUpdateRequest saleUpdateRequest, Sale existingSale) {
        Sale sale = modelMapper.map(saleUpdateRequest, Sale.class);
        sale.setReference(existingSale.getReference());
        sale.setCreatedAt(existingSale.getCreatedAt());
        sale.setCreatedBy(existingSale.getCreatedBy());
        sale.setActive(existingSale.getActive());
        sale.setUpdatedAt(new Date());
        sale.setUpdatedBy(sale.getBiller());
        return setDueAndStatus(sale);
    }

    private Sale setDueAndStatus(Sale sale){
        //due payment
        sale.setDue(sale.getGrandTotal() - sale.getPaid());
        //set status values
        boolean isPaymentIncomplete = sale.getGrandTotal() > sale.getPaid();
        sale.setStatus(isPaymentIncomplete ? Status.Pending : Status.Completed);
        sale.setPaymentStatus(isPaymentIncomplete ? PaymentStatus.Due : PaymentStatus.Paid);
        return sale;
    }

}





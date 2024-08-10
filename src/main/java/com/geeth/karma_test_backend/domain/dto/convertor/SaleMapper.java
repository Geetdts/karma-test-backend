package com.geeth.karma_test_backend.domain.dto.convertor;

import com.geeth.karma_test_backend.application.request.SaleCreateRequest;
import com.geeth.karma_test_backend.application.request.SaleUpdateRequest;
import com.geeth.karma_test_backend.domain.dto.SaleDto;
import com.geeth.karma_test_backend.external.entity.PaymentStatus;
import com.geeth.karma_test_backend.external.entity.Sale;
import com.geeth.karma_test_backend.external.entity.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Converts a SaleCreateRequest object to a Sale entity.
     *
     * @param saleCreateRequest the SaleCreateRequest object containing the details for the new sale.
     * @return the Sale entity created from the request object.
     */
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

    /**
     * Converts a Sale entity to a SaleDto.
     *
     * @param sale the Sale entity to convert.
     * @return the SaleDto object created from the Sale entity.
     */
    public SaleDto convertToDto(Sale sale) {
        return modelMapper.map(sale, SaleDto.class);
    }

    /**
     * Converts a list of Sale entities to a list of SaleDto objects.
     *
     * @param saleList the list of Sale entities to convert.
     * @return a list of SaleDto objects created from the Sale entities.
     */
    public List<SaleDto> convertToDtoList(List<Sale> saleList) {
        return saleList.stream()
                .map(sale -> {
                    SaleDto saleDto = modelMapper.map(sale, SaleDto.class);
                    return saleDto;
                })
                .collect(Collectors.toList());
    }

    /**
     * Converts a SaleUpdateRequest object to a Sale entity, updating the existing Sale entity.
     *
     * @param saleUpdateRequest the SaleUpdateRequest object containing the updated details for the sale.
     * @param existingSale      the existing Sale entity to update.
     * @return the updated Sale entity.
     */
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

    /**
     * Sets the due amount, status, and payment status for the Sale entity based on the total and paid amounts.
     *
     * @param sale the Sale entity to update.
     * @return the updated Sale entity with the due amount, status, and payment status set.
     */
    private Sale setDueAndStatus(Sale sale) {
        //due payment
        sale.setDue(sale.getGrandTotal() - sale.getPaid());
        //set status values
        boolean isPaymentIncomplete = sale.getGrandTotal() > sale.getPaid();
        sale.setStatus(isPaymentIncomplete ? Status.Pending : Status.Completed);
        sale.setPaymentStatus(isPaymentIncomplete ? PaymentStatus.Due : PaymentStatus.Paid);
        return sale;
    }

}





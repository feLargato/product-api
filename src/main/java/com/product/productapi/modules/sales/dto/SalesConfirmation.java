package com.product.productapi.modules.sales.dto;

import com.product.productapi.modules.sales.enums.SalesStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesConfirmation {


    private String salesId;
    private SalesStatus status;

}

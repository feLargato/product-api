package com.product.productapi.modules.product.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.productapi.modules.product.dto.ProductStock;
import com.product.productapi.modules.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductStockListener {


    @Autowired
    private ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void receiveMessageFromProductStock(ProductStock productStock) throws JsonProcessingException {
        log.info("recebendo mensage: {}", new ObjectMapper().writeValueAsString(productStock));
        productService.updateProductStock(productStock);
    }

}

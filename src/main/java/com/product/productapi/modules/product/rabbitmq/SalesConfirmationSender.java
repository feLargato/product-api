package com.product.productapi.modules.product.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.productapi.modules.sales.dto.SalesConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesConfirmationSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${app-config.rabbit.routingKey.sales-confirmation}")
    private String salesConfirmationKey;

    public void sendSalesConfirmationMessage(SalesConfirmation salesConfirmation) {
        try {
            log.info("Sending message: {}", new ObjectMapper().writeValueAsString(salesConfirmation));
            rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationKey, salesConfirmation);
            log.info("Message send successfully");
        }
        catch (Exception e) {
            log.info("Error occurred sending sales confirmation message. Error: " + e.getMessage());
        }
    }

}

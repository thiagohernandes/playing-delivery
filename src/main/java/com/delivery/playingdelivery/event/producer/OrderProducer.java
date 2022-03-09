package com.delivery.playingdelivery.event.producer;

import com.delivery.playingdelivery.event.payload.OrderPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProducer {

    private final KafkaTemplate<String, OrderPayload> orderKafkaTemplate;

    public void sendOrder(OrderPayload orderPayload, String topicName) {
        log.info("------------------ Sending order {} ----------------", orderPayload);
        orderKafkaTemplate.send(topicName, orderPayload);
    }
}

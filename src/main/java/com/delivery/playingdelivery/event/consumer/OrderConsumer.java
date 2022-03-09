package com.delivery.playingdelivery.event.consumer;

import com.delivery.playingdelivery.event.constants.EventConstants;
import com.delivery.playingdelivery.event.payload.OrderPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = EventConstants.TOPIC_CREATED_ORDER,
        groupId = EventConstants.GROUP_CREATED_ORDER,
        containerFactory = "userKafkaListenerContainerFactory")
    void listener(OrderPayload orderPayload) {
        log.info("------------------ Received order {} ----------------", orderPayload);
    }
}

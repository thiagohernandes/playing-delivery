package com.delivery.playingdelivery.event.config;

import com.delivery.playingdelivery.event.constants.EventConstants;
import com.delivery.playingdelivery.event.payload.OrderPayload;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, OrderPayload> orderConsumerFactory() {
        final Map<String, Object> config =
            Map.of(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, EventConstants.BOOTSTRAP_SERVERS_CONFIG,
                ProducerConfig.CLIENT_ID_CONFIG, EventConstants.ID_CLIENT_CREATED_ORDER,
                ConsumerConfig.GROUP_ID_CONFIG, EventConstants.GROUP_CREATED_ORDER);

        return new DefaultKafkaConsumerFactory<>(
            config,
            new StringDeserializer(),
            new JsonDeserializer<>(OrderPayload.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderPayload> userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderPayload> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(orderConsumerFactory());

        return factory;
    }
}

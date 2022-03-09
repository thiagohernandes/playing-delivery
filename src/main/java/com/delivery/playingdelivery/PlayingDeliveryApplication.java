package com.delivery.playingdelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlayingDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayingDeliveryApplication.class, args);
    }
}

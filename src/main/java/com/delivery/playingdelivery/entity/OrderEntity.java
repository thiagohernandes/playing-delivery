package com.delivery.playingdelivery.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity(name = "tbl_order")
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "date_order", nullable = false)
    private LocalDate dateOrder;
    @Column(name = "provider_id", nullable = false)
    private Long providerId;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
}

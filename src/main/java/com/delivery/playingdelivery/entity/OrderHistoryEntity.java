package com.delivery.playingdelivery.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "tbl_order_history")
@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "status", nullable = false)
    private Integer status;
    @Column(name = "order_id", nullable = false)
    private Long orderId;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @PrePersist
    void prePersist() {
        this.created = LocalDateTime.now();
    }
}

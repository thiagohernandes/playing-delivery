package com.delivery.playingdelivery.repository;

import com.delivery.playingdelivery.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}

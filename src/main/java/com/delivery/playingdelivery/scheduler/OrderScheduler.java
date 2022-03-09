package com.delivery.playingdelivery.scheduler;

import com.delivery.playingdelivery.entity.*;
import com.delivery.playingdelivery.enums.OrderHistoryEnum;
import com.delivery.playingdelivery.event.constants.EventConstants;
import com.delivery.playingdelivery.event.payload.OrderPayload;
import com.delivery.playingdelivery.event.producer.OrderProducer;
import com.delivery.playingdelivery.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class OrderScheduler {

    private final CustomerRepository customerRepository;
    private final ProviderRepository providerRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final OrderProducer orderProducer;

    @SuppressWarnings("checkstyle:MagicNumber")
    @Scheduled(fixedRate = 5000)
    void init() {
        loadInitialData();
        loadOrderData();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private void loadOrderData() {
        log.info("------------------ ORDERS ------------------");
        int quantityProducts = getRandomNumber(1, 6);
        List<CustomerEntity> customerList = customerRepository.findAll();
        Collections.shuffle(customerList);
        List<ProviderEntity> providerList = providerRepository.findAll();
        Collections.shuffle(providerList);

        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerList
            .get(getRandomNumber(1, 2)).getId());
        Optional<ProviderEntity> providerEntity = providerRepository.findById(providerList
            .get(getRandomNumber(1, 2)).getId());

        if (customerEntity.isPresent() && providerEntity.isPresent()) {
            final OrderEntity orderEntity = orderRepository.save(OrderEntity.builder()
                .dateOrder(LocalDate.now())
                .customerId(customerEntity.get().getId())
                .providerId(providerEntity.get().getId())
                .build());

            final Long orderId = orderEntity.getId();

            log.info("------------------- Order generated ->>>> {} ------------------------", orderId);

            orderHistoryRepository.save(OrderHistoryEntity.builder()
                .status(OrderHistoryEnum.CREATED.getCode())
                .orderId(orderId)
                .build());

            int controlOrderItem = 1;

            while (controlOrderItem <= quantityProducts) {
                List<ProductEntity> productList = productRepository.findAll();
                Collections.shuffle(productList);
                Optional<ProductEntity> productEntity = productRepository.findById(productList
                    .get(getRandomNumber(1, 2)).getId());

                productEntity.ifPresent(entity -> orderItemRepository.save(OrderItemEntity.builder()
                    .orderId(orderId)
                    .productId(entity.getId())
                    .price(entity.getPrice())
                    .quantity(getRandomNumber(1, 10))
                    .build()));
                controlOrderItem++;
            }

            orderProducer.sendOrder(OrderPayload.builder().id(orderEntity.getId())
                    .dateOrder(orderEntity.getDateOrder())
                    .customerId(orderEntity.getCustomerId())
                    .providerId(orderEntity.getProviderId())
                    .build(),
                EventConstants.TOPIC_CREATED_ORDER);
        }
    }

    public int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    private void loadInitialData() {
        log.info("------------------ CUSTOMERS ------------------");

        if (customerRepository.findAll().isEmpty()) {
            Iterable<CustomerEntity> customerEntities = Arrays.asList(
                CustomerEntity.builder().cpf(12345678966L).name("Customer 1").build(),
                CustomerEntity.builder().cpf(10225844116L).name("Customer 2").build(),
                CustomerEntity.builder().cpf(41136699544L).name("Customer 3").build(),
                CustomerEntity.builder().cpf(10235589541L).name("Customer 4").build()
            );
            customerRepository.saveAll(customerEntities);
        }

        log.info("------------------ PROVIDERS ------------------");

        if (providerRepository.findAll().isEmpty()) {
            Iterable<ProviderEntity> providerEntities = Arrays.asList(
                ProviderEntity.builder().name("Provider 1").build(),
                ProviderEntity.builder().name("Provider 2").build(),
                ProviderEntity.builder().name("Provider 3").build(),
                ProviderEntity.builder().name("Provider 4").build()
            );
            providerRepository.saveAll(providerEntities);
        }

        log.info("------------------ PRODUCTS ------------------");

        if (productRepository.findAll().isEmpty()) {
            Iterable<ProductEntity> providerEntities = Arrays.asList(
                ProductEntity.builder()
                    .description("Product 1")
                    .price(new BigDecimal("137.88"))
                    .stock(100).build(),
                ProductEntity.builder()
                    .description("Product 2")
                    .price(new BigDecimal("200.77"))
                    .stock(50).build(),
                ProductEntity.builder()
                    .description("Product 3")
                    .price(new BigDecimal("88.21"))
                    .stock(360).build(),
                ProductEntity.builder()
                    .description("Product 4")
                    .price(new BigDecimal("137.88"))
                    .stock(190).build(),
                ProductEntity.builder()
                    .description("Product 5")
                    .price(new BigDecimal("52.10"))
                    .stock(5000).build(),
                ProductEntity.builder()
                    .description("Product 6")
                    .price(new BigDecimal("19.77"))
                    .stock(407).build()
            );
            productRepository.saveAll(providerEntities);
        }
    }
}

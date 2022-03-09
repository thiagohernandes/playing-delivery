package com.delivery.playingdelivery.enums;

import lombok.Getter;

@Getter
public enum OrderHistoryEnum {
    CREATED(1, "CREATED"),
    WAITING_PAYMENT(2, "WAITING_PAYMENT"),
    NOTIFY_PROVIDER(3, "NOTIFY_PROVIDER"),
    START_DELIVERY(4, "START_DELIVERY"),
    NOTIFY_CUSTOMER(5, "NOTIFY_CUSTOMER");

    private final Integer code;
    private final String status;

    OrderHistoryEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}

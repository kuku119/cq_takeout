package com.kk119.cq_takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersPaymentDTO implements Serializable {
    // 订单号
    private String orderNumber;

    // 付款方式：0微信，1支付宝
    private Integer payMethod;
}

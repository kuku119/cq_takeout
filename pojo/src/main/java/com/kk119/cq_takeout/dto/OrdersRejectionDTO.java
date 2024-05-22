package com.kk119.cq_takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrdersRejectionDTO implements Serializable {
    private Long id;

    // 订单拒绝原因
    private String rejectionReason;
}

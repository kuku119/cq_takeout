package com.kk119.cq_takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 名称
    private String name;

    // 订单 id
    private Long orderId;

    // 菜品 id
    private Long dishId;

    // 套餐 id
    private Long setmealId;

    // 口味
    private String dishFlavor;

    // 数量
    private Integer number;

    // 金额
    private BigDecimal amount;

    // 图片
    private String image;
}

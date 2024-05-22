package com.kk119.cq_takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishFlavor implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 菜 id
    private Long dishId;

    // 口味名称
    private String name;

    // 口味数据
    private String value;
}

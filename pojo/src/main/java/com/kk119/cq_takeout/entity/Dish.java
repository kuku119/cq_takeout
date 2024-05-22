package com.kk119.cq_takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dish implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 菜名
    private String name;

    // 菜分类 id
    private Long categoryId;

    // 菜价
    private BigDecimal price;

    // 图片
    private String image;

    // 描述
    private String description;

    // 状态：0停售，1可售
    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}

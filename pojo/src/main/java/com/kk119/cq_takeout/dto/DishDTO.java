package com.kk119.cq_takeout.dto;

import com.kk119.cq_takeout.entity.DishFlavor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDTO implements Serializable {
    private Long id;
    // 菜名
    private String name;
    // 菜品分类 id
    private Long categoryId;
    // 菜价
    private BigDecimal price;
    // 菜图
    private String image;
    // 描述
    private String description;
    // 售卖状态：1可售，0停售
    private Integer status;
    // 口味
    private List<DishFlavor> flavors = new ArrayList<>();
}

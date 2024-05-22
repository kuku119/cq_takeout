package com.kk119.cq_takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DishPageQueryDTO implements Serializable {
    private Integer page;
    private Integer pageSize;
    private String name;
    // 分类编号
    private Integer categoryId;
    // 状态；0禁用，1启用
    private Integer status;
}

package com.kk119.cq_takeout.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 类型：1菜品分类，2套餐分类
    private Integer type;

    // 分类名称
    private String name;

    // 顺序
    private Integer sort;

    // 状态：0禁用，1启用
    private Integer status;

    // 创建时间
    private LocalDateTime createTime;

    // 更新时间
    private LocalDateTime updateTime;

    // 创建者
    private Long createUser;

    // 更新者
    private Long updateUser;
}

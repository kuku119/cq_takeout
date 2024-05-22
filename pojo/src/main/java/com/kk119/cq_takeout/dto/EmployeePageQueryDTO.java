package com.kk119.cq_takeout.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeePageQueryDTO implements Serializable {
    // 员工姓名
    private String name;

    // 页码
    private Integer page;

    // 每页记录数
    private Integer pageSize;
}

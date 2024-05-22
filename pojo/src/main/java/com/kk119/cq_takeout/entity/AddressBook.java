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
public class AddressBook implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    // 用户 id
    private Long userId;

    // 收货人
    private String consignee;

    // 手机号
    private String phone;

    // 性别：0女，1男
    private String sex;

    // 省级区划编号
    private String provinceCode;

    // 省级名称
    private String provinceName;

    // 市级区划编号
    private String cityCode;

    // 市级名称
    private String cityName;

    //区级区划编号
    private String districtCode;

    //区级名称
    private String districtName;

    // 详细地址
    private String detail;

    // 标签
    private String label;

    // 是否为默认？0否，1是
    private Integer isDefault;
}

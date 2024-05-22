package com.kk119.cq_takeout.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusinessDataVO implements Serializable {

    /**
     * 营业额
     */
    private Double turnover;

    /**
     * 有效订单数
     */
    private Integer validOrderCount;

    /**
     * 订单完成率
     */
    private Double orderCompletionRate;

    /**
     * 平均客单价
     */
    private Double unitPrice;

    /**
     * 新增用户数
     */
    private Integer newUsers;

}

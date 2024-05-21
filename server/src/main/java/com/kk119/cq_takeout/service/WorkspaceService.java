package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.vo.BusinessDataVO;
import com.kk119.cq_takeout.vo.DishOverViewVO;
import com.kk119.cq_takeout.vo.OrderOverViewVO;
import com.kk119.cq_takeout.vo.SetmealOverViewVO;

import java.time.LocalDate;

public interface WorkspaceService {
    /**
     * 根据时间段统计营业额
     *
     * @param begin
     * @param end
     * @return {@link BusinessDataVO }
     */
    BusinessDataVO getBusinessData(LocalDate begin, LocalDate end);

    /**
     * 查询订单管理数据
     *
     * @return {@link OrderOverViewVO }
     */
    OrderOverViewVO getOrderOverView();

    /**
     * 查询菜品总览
     *
     * @return {@link DishOverViewVO }
     */
    DishOverViewVO getDishOverView();

    /**
     * 查询套餐总览
     *
     * @return {@link SetmealOverViewVO }
     */
    SetmealOverViewVO getSetmealOverView();
}

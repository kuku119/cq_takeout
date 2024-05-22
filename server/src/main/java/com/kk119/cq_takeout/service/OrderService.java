package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.OrdersCancelDTO;
import com.kk119.cq_takeout.dto.OrdersConfirmDTO;
import com.kk119.cq_takeout.dto.OrdersPageQueryDTO;
import com.kk119.cq_takeout.dto.OrdersPaymentDTO;
import com.kk119.cq_takeout.result.PageResult;
import com.kk119.cq_takeout.vo.OrderPaymentVO;
import com.kk119.cq_takeout.vo.OrderStatisticsVO;
import com.kk119.cq_takeout.vo.OrderVO;

public interface OrderService {
    /**
     * 订单支付
     *
     * @param ordersPaymentDTO ordersPaymentDTO
     * @return {@link OrderPaymentVO }
     * @throws Exception
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     * 支付成功
     *
     * @param outTradeNo outTradeNo
     */
    void paySuccess(String outTradeNo);

    OrderVO details(Long orderId);

    /**
     * 用户端订单分页查询
     *
     * @param page page
     * @param pageSize pageSize
     * @param status status
     * @return {@link PageResult }
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     * 用户取消订单
     *
     * @param id id
     */
    void userCancelById(Long id);

    /**
     * 再来一单
     *
     * @param id id
     */
    void repetition(Long id);

    /**
     * 按条件搜索订单
     *
     * @param ordersPageQueryDTO ordersPageQueryDTO
     * @return {@link PageResult }
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 统计各状态的订单数量
     *
     * @return {@link OrderStatisticsVO }
     */
    OrderStatisticsVO statistics();

    void delivery(Long id);

    void complete(Long id);

    /**
     * 接单
     *
     * @param ordersConfirmDTO ordersConfirmDTO
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 商家取消订单
     *
     * @param ordersCancelDTO ordersCancelDTO
     * @throws Exception
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    void reminder(Long id);
}

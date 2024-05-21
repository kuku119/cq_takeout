package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.DishDTO;
import com.kk119.cq_takeout.dto.DishPageQueryDTO;
import com.kk119.cq_takeout.entity.Dish;
import com.kk119.cq_takeout.result.PageResult;
import com.kk119.cq_takeout.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * 新增菜品和口味数据
     *
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    DishVO getByIdWithFlavor(Long dishId);

    void deleteByIds(List<Long> dishIds);

    void update(DishVO dishVO);

    /**
     * 条件查询菜品和口味
     *
     * @param dish
     * @return {@link List }<{@link DishVO }>
     */
    List<DishVO> listWithFlavor(Dish dish);

    List<Dish> list(Long categoryId);
}

package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.SetmealDTO;
import com.kk119.cq_takeout.dto.SetmealPageQueryDTO;
import com.kk119.cq_takeout.entity.Setmeal;
import com.kk119.cq_takeout.result.PageResult;
import com.kk119.cq_takeout.vo.DishItemVO;

import java.util.List;

public interface SetmealService {
    /**
     * 新增套餐，同时保存套餐与菜品的关系
     *
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 分页查询
     *
     * @param setmealPageQueryDTO
     * @return {@link PageResult }
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     * 批量删除套餐
     *
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 动态条件查询套餐
     *
     * @param setmeal
     * @return {@link List }<{@link SetmealDTO }>
     */
    List<SetmealDTO> list(Setmeal setmeal);

    /**
     * 根据编号查询菜品
     *
     * @param id
     * @return {@link List }<{@link DishItemVO }>
     */
    List<DishItemVO> getDishItemById(Long id);
}

package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.CategoryDTO;
import com.kk119.cq_takeout.dto.CategoryPageQueryDTO;
import com.kk119.cq_takeout.result.PageResult;

import java.util.List;

public interface CategoryService {
    /**
     * 添加分类
     *
     * @param categoryDTO categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询
     *
     * @param categoryPageQueryDTO categoryPageQueryDTO
     * @return {@link PageResult }
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 按 id 删除分类
     *
     * @param id id
     */
    void deleteById(Long id);

    /**
     * 修改分类
     *
     * @param categoryDTO categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 启用或禁用分类
     *
     * @param status status
     * @param id id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据类型查询分类
     *
     * @return {@link List }<{@link CategoryDTO }>
     */
    List<CategoryDTO> list(Integer type);
}

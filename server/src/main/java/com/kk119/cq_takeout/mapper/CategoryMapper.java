package com.kk119.cq_takeout.mapper;

import com.github.pagehelper.Page;
import com.kk119.cq_takeout.annotation.AutoFill;
import com.kk119.cq_takeout.dto.CategoryPageQueryDTO;
import com.kk119.cq_takeout.entity.Category;
import com.kk119.cq_takeout.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 插入数据
     */
    @Insert("insert into category "
            + "(type, name, sort, status, create_time, update_time, create_user, update_user) "
            + "values "
            + "(#{type}, #{name}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    void insert(Category category);

    /**
     * 分页查询
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据 id 删除分类
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据 id 修改分类
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 根据类型查询分类
     */
    List<Category> list(Integer type);
}

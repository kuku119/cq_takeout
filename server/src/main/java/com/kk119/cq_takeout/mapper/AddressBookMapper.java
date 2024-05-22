package com.kk119.cq_takeout.mapper;

import com.kk119.cq_takeout.entity.AddressBook;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddressBookMapper {
    /**
     * 条件查询
     *
     * @param addressBook addressBook
     */
    List<AddressBook> list(AddressBook addressBook);

    /**
     * 插入
     */
    @Insert("insert into address_book" +
            " (user_id, consignee, phone, sex, province_code, province_name, city_code, city_name, district_code, district_name, detail, label, is_default)" +
            " values " +
            " (#{userId}, #{consignee}, #{phone}, #{sex}, #{provinceCode}, #{provinceName}, #{cityCode}, #{cityName}, #{districtCode}, #{districtName}, #{detail}, #{label}, #{isDefault})")
    void insert(AddressBook addressBook);

    /**
     * 根据 id 修改
     */
    @Select("select * from address_book where id = #{id}")
    AddressBook getById(Long id);

    /**
     * 根据用户 id 修改是否为默认地址
     */
    @Update("update address_book set is_default = #{isDefault} where user_id = #{userId}")
    void updateIsDefaultByUserId(AddressBook addressBook);

    /**
     * 根据 id 删除地址
     */
    @Delete("delete from address_book where id = #{id}")
    void deleteById(Long id);
}

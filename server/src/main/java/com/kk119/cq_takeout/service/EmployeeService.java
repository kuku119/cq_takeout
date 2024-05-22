package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.EmployeeDTO;
import com.kk119.cq_takeout.dto.EmployeePageQueryDTO;
import com.kk119.cq_takeout.entity.Employee;
import com.kk119.cq_takeout.result.PageResult;

public interface EmployeeService {
    /**
     * 员工登录
     *
     * @param employeeDTO employeeDTO
     * @return {@link Employee }
     */
    Employee login(EmployeeDTO employeeDTO);

    void save(EmployeeDTO employeeDTO);

    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    Employee getById(String id);

    int update(Employee employee);

    int updateStatus(Long id, Integer status);
}

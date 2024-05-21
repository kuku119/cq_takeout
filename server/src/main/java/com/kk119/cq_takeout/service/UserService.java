package com.kk119.cq_takeout.service;

import com.kk119.cq_takeout.dto.UserLoginDTO;
import com.kk119.cq_takeout.entity.User;

public interface UserService {
    /**
     * 微信登录
     *
     * @param userLoginDTO
     * @return {@link User }
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}

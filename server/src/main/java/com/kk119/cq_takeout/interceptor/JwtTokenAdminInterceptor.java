package com.kk119.cq_takeout.interceptor;

import com.kk119.cq_takeout.constant.JwtClaimsConstant;
import com.kk119.cq_takeout.context.BaseContext;
import com.kk119.cq_takeout.properties.JwtProperties;
import com.kk119.cq_takeout.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验 jwt
     *
     * @param request  request
     * @param response response
     * @param handler  handler
     * @return boolean
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截的是 controller 的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            // 如果拦截的不是动态方法，直接放行
            return true;
        }

        // 1.从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        // 2.校验令牌
        try {
            log.info("jwt 校验：{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            log.info("当前员工 id：{}", empId);
            BaseContext.setCurrentId(empId);

            // 3.1. 通过，放行
            return true;
        } catch (Exception e) {
            // 3.2. 不通过，响应 401 状态码
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

    }
}

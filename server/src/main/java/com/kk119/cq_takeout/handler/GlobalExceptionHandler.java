package com.kk119.cq_takeout.handler;

import com.kk119.cq_takeout.constant.MessageConstant;
import com.kk119.cq_takeout.exception.BaseException;
import com.kk119.cq_takeout.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 *
 * @author Jiang
 * @date 2024/05/23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(BaseException be) {
        log.error("异常信息：{}", be.getMessage());
        return Result.error(be.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String exMessage = ex.getMessage();
        if (exMessage.contains("Duplicate entry")) {
            String msg = exMessage.split(" ")[2] + MessageConstant.ACCOUNT_EXISTS;
            return Result.error(msg);
        }
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }
}

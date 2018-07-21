package com.seawater.mybatisPlus.utils;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 * Created by zhouhaishui on 2018/7/3.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handleException(Exception e){
        logger.error("调用接口出现异常,错误信息" + e.toString());
        logger.info("调用接口出现异常,错误信息" + e.toString());
        ResultVo result = new ResultVo();
        result.setError_no(ResultVo.ErrorCode.FAILURE);
        result.setError_info(ResultVo.ErrorMessage.FAILURE);
        return result;

    }


}

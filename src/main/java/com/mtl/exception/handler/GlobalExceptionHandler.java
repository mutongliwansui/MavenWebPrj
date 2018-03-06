package com.mtl.exception.handler;

import com.mtl.exception.BusinessException;
import com.mtl.exception.ValidationException;
import com.mtl.vo.ResultVo;
import org.apache.log4j.Logger;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 * Created by mutongli on 2018/02/21
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    //日志打印
    private static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    /**
     * 基类异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class) //如果直接@ExceptionHandler,则默认使用handle方法的异常类参数类型
    @ResponseBody
    public ResultVo handle(Throwable ex){
        LOGGER.error("Throwable:"+ex.getMessage(),ex);
        return ResultVo.newErrorInstance("系统异常错误！");
    }

    /**
     * 调用方式错误
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)//如果直接@ExceptionHandler,则默认使用handle方法的异常类参数类型
    @ResponseBody
    public ResultVo handle(HttpRequestMethodNotSupportedException ex){
        LOGGER.error("HttpRequestMethodNotSupportedException:"+ex.getMessage(),ex);
        return ResultVo.newErrorInstance("Web服务调用方式错误！");
    }

    /**
     * 校验服务参数
     * @param ex
     * @return
     */
    @ExceptionHandler(ValidationException.class)//如果直接@ExceptionHandler,则默认使用handle方法的异常类参数类型
    @ResponseBody
    public ResultVo handle(ValidationException ex){
        LOGGER.error("ValidationException:"+ex.getMessage(),ex);
        return ResultVo.newValidFailInstance("参数校验失败！",ex.getData());
    }
    /**
     * 业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResultVo handle(BusinessException ex){
        LOGGER.info("BusinessException:"+ex.getMessage());
        return ResultVo.newFailInstance(ex.getMessage(),null);
    }
}

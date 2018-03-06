package com.mtl.vo;

import java.io.Serializable;

public class ResultVo implements Serializable{
    public static final String CODE_SUCCESS = "success"; //返回结果类型-成功
    public static final String CODE_VALIDFAIL = "validfail"; //返回结果类型-校验失败
    public static final String CODE_FAIL = "fail"; //返回结果类型-业务失败
    public static final String CODE_ERROR = "error"; //返回结果类型-系统错误

    private String code; //返回结果类型
    private String message; //返回结果信息
    private Object data; //返回数据

    public static ResultVo newInstance(String code,String message,Object data) {
         ResultVo resultVo = new ResultVo();
         resultVo.setCode(code);
         resultVo.setMessage(message);
         resultVo.setData(data);
         return resultVo;
    }

    public static ResultVo newSucInstance(String message,Object data){
        return newInstance(CODE_SUCCESS,message,data);
    }

    public static ResultVo newFailInstance(String message,Object data){
        return newInstance(CODE_FAIL,message,data);
    }

    public static ResultVo newValidFailInstance(String message,Object data){
        if(null == message){
            message = "the validation is failed !";
        }
        return newInstance(CODE_VALIDFAIL,message,data);
    }

    public static ResultVo newErrorInstance(String message){
        return newInstance(CODE_ERROR,message,null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

package com.mtl.validation.parser;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类，用于处理校验结果
 * @author  mutongli
 * @date 2018/02/25
 */
public class BindingResultParser {
    /**
     * 把BindingResult 转化为Map
     * @param result
     * @return
     */
    public static Map<String,String> parseMap(BindingResult result){
        Map<String,String> resmap = new HashMap<String,String>();
        for (ObjectError error : result.getAllErrors()) {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String errmsg = fieldError.getDefaultMessage();
            resmap.put(field,errmsg);
        }
        return resmap;
    }
}

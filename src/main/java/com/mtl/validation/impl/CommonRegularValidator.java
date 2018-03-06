package com.mtl.validation.impl;

import com.mtl.validation.CommonRegular;
import org.apache.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CommonRegularValidator implements ConstraintValidator<CommonRegular,String>{
    private static Logger LOGGER = Logger.getLogger(CommonRegularValidator.class);
    private Pattern pattern = null;
    @Override
    public void initialize(CommonRegular regular) {
        String reg = regular.regexp();
        if(null == reg){
            LOGGER.error("the valid pattern could not be null");
            return;
        }
        pattern = Pattern.compile(reg);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(null == value){
            return true;
        }
        if(null == pattern){
            return false;
        }
        return pattern.matcher(value).matches();
    }
}

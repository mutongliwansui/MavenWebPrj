package com.mtl.service.impl;

import com.mtl.exception.BusinessException;
import com.mtl.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService{
    @Override
    public void demoMethod1() {
        if(1!=1){
            throw new BusinessException("业务异常！");
        }
    }
}

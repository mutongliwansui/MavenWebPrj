package com.mtl.service;

import com.mtl.controller.DemoController;
import com.mtl.exception.BusinessException;
import com.mtl.exception.handler.GlobalExceptionHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemoService {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring/context/app-context.xml");
    @Test
    public void testService(){
        DemoService demoService = context.getBean(DemoService.class);
        demoService.demoMethod1();
    }
    @Test
    public void testController() throws Exception {
        DemoController demoController = context.getBean(DemoController.class);
        demoController.demo();
    }
    @Test
    public void testExceptionHandler(){
        GlobalExceptionHandler handler = context.getBean(GlobalExceptionHandler.class);
        handler.handle(new BusinessException("业务异常"));
    }
}

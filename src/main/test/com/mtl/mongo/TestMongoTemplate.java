package com.mtl.mongo;

import com.mtl.vo.ResultVo;
import com.mtl.vo.UserVo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

public class TestMongoTemplate {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring/context/app-context.xml");
    @Test
    public void testAddUser(){
        BlockingQueue queue;
        UserVo user = new UserVo();
        user.setBirthday(new Date());
        user.setHeight(172);
        user.setWeight(152.5);
        user.setName("林勇");
        MongoTemplate template = context.getBean("mongoTemplate", MongoTemplate.class);
        ResultVo resultVo = ResultVo.newSucInstance("成功了！",user);
        template.insert(resultVo);
    }
}

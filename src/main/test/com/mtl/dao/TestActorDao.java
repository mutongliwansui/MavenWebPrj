package com.mtl.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestActorDao {
    ApplicationContext context = new ClassPathXmlApplicationContext("/spring/context/app-context.xml");

    @Autowired
    private ActorDao actorDao;

    @Test
    public void testSave(){
        actorDao.findAllByFirstname("Ting");
    }
}

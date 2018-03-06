package com.mtl.service.impl;

import com.mtl.dao.ActorDao;
import com.mtl.exception.BusinessException;
import com.mtl.po.Actor;
import com.mtl.service.ActorService;
import com.mtl.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActorServiceImpl implements ActorService{
    @Autowired
    private ActorDao actorDao;

    @Override
    @Transactional
    public Actor save(Actor actor) {
        String idcard = actor.getIdcard();
        Actor rt_actor = actorDao.findByIdcard(idcard);
        if(null != rt_actor){
            throw new BusinessException("the Actor[idcard:"+idcard+"] has exists,could not save again!");
        }
        return actorDao.save(actor);
    }

    @Override
    public Actor get(int actorid) {
        Actor actor = actorDao.findByActorid(actorid);
        if(null == actor){
            throw new BusinessException("the actor with id ["+actorid+"] can not found!",null);
        }
        return actor;
    }

    @Override
    public List<Actor> findAllByFirstname(String firstname) {
        return actorDao.findAllByFirstname(firstname);
    }
}

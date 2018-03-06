package com.mtl.service;

import com.mtl.po.Actor;

import java.util.List;

public interface ActorService {
    /**
     * 保存演员信息
     * @param actor
     * @return
     */
    public Actor save(Actor actor);

    /**
     * 根据演员id获取演员信息
     * @param actorid
     * @return
     */
    public Actor get(int actorid);

    /**
     * 通过名字获取演员信息
     * @param firstname 名
     * @return
     */
    public List<Actor> findAllByFirstname(String firstname);
}

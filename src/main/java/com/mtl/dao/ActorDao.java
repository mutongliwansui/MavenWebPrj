package com.mtl.dao;

import com.mtl.po.Actor;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Table(name = "actor")
//@Qualifier("actorRepository")
public interface ActorDao extends CrudRepository<Actor,Integer> {
    /**
     * 根据主键获取实体
     * @param id
     * @return
     */
    public Actor findByActorid(int id);

    /**
     * 根据证件号码获取实体
     * @param idcard
     * @return
     */
    public Actor findByIdcard(String idcard);

    /**
     * 保存实体
     * @param actor
     * @return
     */
    public Actor save(Actor actor);

    /**
     * 根据firstname查询实体集合
     * @param firstname
     * @return
     */
    @Query("select t from Actor t where t.firstname=:firstname")
    public List<Actor> findAllByFirstname(@Param("firstname") String firstname);

    /**
     * 根据姓-名查找演员
     * @param firstname 名
     * @param lastname 姓
     * @return
     */
    @Query("select t from Actor  t where t.firstname=:firstname and t.lastname=:lastname")
    public List<Actor> findAllByFirstnameAndLastname(@Param("firstname") String firstname, @Param("lastname") String lastname);
}

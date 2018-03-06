package com.mtl.controller;

import com.mtl.po.Actor;
import com.mtl.service.ActorService;
import com.mtl.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ActorController {
    @Autowired
    private ActorService actorService;

    /**
     * 根据演员id获取演员信息
     * @param actorid
     * @return
     */
    @RequestMapping("/getActor/{actorid}")
    public ResultVo queryById(@PathVariable("actorid") int actorid){
        return ResultVo.newSucInstance("has success found Actor",actorService.get(actorid));
    }

    /**
     * 根据名查找演员
     * @param firstname 名
     * @return
     */
    @RequestMapping("/queryByFirstname/{firstname}")
    public ResultVo queryByFirstname(@PathVariable("firstname") String firstname){
        return ResultVo.newSucInstance("has success excute query",actorService.findAllByFirstname(firstname));
    }

    @RequestMapping("/saveActor")
    public ResultVo saveActor(@Valid Actor actor, BindingResult result){
        return ResultVo.newSucInstance("success excute save",actorService.save(actor));
    }
}

package com.mtl.controller;

import com.mtl.exception.BusinessException;
import com.mtl.service.DemoService;
import com.mtl.validation.parser.BindingResultParser;
import com.mtl.vo.ResultVo;
import com.mtl.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    private DemoService demoService; //也可以使用@Resource(name="demoService")，不过Bean定义需要指定Id

    @ResponseBody
    @RequestMapping(value = "/demo")
    public ResultVo demo() throws Exception {
        System.out.println("demo");
        if(1==1){
            throw new Exception("该死的，出错了！");
        }
        return ResultVo.newSucInstance("该死的，竟然成功了！",1);
    }

    @ResponseBody
    @RequestMapping(value = "/demo2")
    public ResultVo demo2(){
        System.out.println("demo2");
        demoService.demoMethod1();
        return ResultVo.newSucInstance("该死的，竟然成功了！",1);
    }

    @ResponseBody
    @RequestMapping(value = "/demo3/{myname}")
    public ResultVo demo3(@PathVariable("myname") String name){
        System.out.println("demo3");
        demoService.demoMethod1();
        return ResultVo.newSucInstance("该死的，"+name+"竟然成功了！",1);
    }

    @ResponseBody
    @RequestMapping(value = "/demo4")
    public ResultVo demo4(@RequestParam(value = "myname",required = false,defaultValue = "走你") String name){ //默认required为true
        System.out.println("demo4");
        demoService.demoMethod1();
        return ResultVo.newSucInstance("该死的4，"+name+"竟然成功了！",1);
    }

    @ResponseBody
    @RequestMapping(value = "/demo5",method = RequestMethod.POST)
    public ResultVo demo5(@Valid UserVo user, BindingResult result){ //此处只需传入参数BindingResult即可，剩余的工作交给ValidationAspect执行
        System.out.println("demo5");
        return ResultVo.newSucInstance("该死的5，"+user.getName()+"竟然成功了！",1);
    }

    @ResponseBody
    @RequestMapping(value = "/saveToSession")
    public ResultVo demo6(@Valid UserVo user, BindingResult result, HttpServletRequest request){
        System.out.println("saveToSession");
        HttpSession session = request.getSession();
        session.setAttribute("userinfo",user);
        return ResultVo.newSucInstance("成功存入session！",null);
    }

    @ResponseBody
    @RequestMapping(value = "/getFromSession")
    public ResultVo demo7(HttpServletRequest request){
        System.out.println("getFromSession");
        HttpSession session = request.getSession();
        UserVo user = (UserVo) session.getAttribute("userinfo");
        if(null == user){
            throw new BusinessException("获取用户信息失败，可能是没有保存用户信息或者用户信息存储失效！");
        }
        return ResultVo.newSucInstance("成功从session获取用户信息！",user);
    }
}

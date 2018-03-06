package com.mtl.aspect;

import com.mtl.exception.ValidationException;
import com.mtl.validation.parser.BindingResultParser;
import com.mtl.vo.ResultVo;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Aspect    //该标签把ValidationAspect类声明为一个切面
@Order(1)  //设置切面的优先级：如果有多个切面，可通过设置优先级控制切面的执行顺序（数值越小，优先级越高）
@Component //该标签把ValidationAspect类放到IOC容器中
public class ValidationAspect {
    private static Logger LOGGER = Logger.getLogger(ValidationAspect.class);
    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
     * 使用@Pointcut声明切入点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
    @Pointcut("execution(public * com.mtl.controller.*Controller.*(..))")
    public void methodRange(){};

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("methodRange()") //该标签声明次方法是一个前置通知：在目标方法开始之前执行
    public void beforeMethod(JoinPoint joinPoint){
        //do Nothing
    }
    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    @After("methodRange()")
    public void afterMethod(JoinPoint joinPoint){
        //do Nothing
    }
    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     */
    @AfterReturning(value="methodRange()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        //do Nothing
    }
    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="methodRange()",throwing="ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        //do Nothing
    }
    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     * @param point
     */
    @Around(value="methodRange()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        //do Nothing
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Annotation[][] annotationss = method.getParameterAnnotations(); //方法所有参数的
        boolean cancontinue = false;
        //查找所有的参数注解，看是否有参数被@Valid修饰
        for (Annotation[] annotaions : annotationss) {
            for (Annotation annotation : annotaions){
                if(annotation instanceof Valid){
                    cancontinue = true;
                    break;
                }
            }
            if(cancontinue){
                break;
            }
        }
        if(cancontinue){
            String methodName = method.getName();
            LOGGER.info("this method "+methodName+"");
            Object[] args = point.getArgs();
            //查找方法的参数里是否有BindingResult，如果没有报错提示，不过@Valid修饰的参数后必须跟BindingResult，所以此处的错误提示只是预防作用
            BindingResult result = null;
            for (Object arg : args) {
                if (arg instanceof BindingResult) {
                    result = (BindingResult) arg;
                }
            }
            if(null == result){
                throw new Exception("the arg bingresult can not found!");
            }
            if(result.hasErrors()){ //如果有错误，使用错误信息转换工具方法转换成Map
                throw new ValidationException(BindingResultParser.parseMap(result));
            }
        }
        return point.proceed();
    }
}

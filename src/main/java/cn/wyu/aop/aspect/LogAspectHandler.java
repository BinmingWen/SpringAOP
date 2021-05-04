package cn.wyu.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.RequestHandledEvent;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspectHandler {
    @Before("execution(* cn.wyu.aop.controller..*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("======doBefore方法进入=========");

        //获取签名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        String packageStr = signature.getDeclaringTypeName();
        System.out.println("即将执行的方法："+method + " 所属的包名："+packageStr);

        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        String addr = request.getRemoteAddr();
        System.out.println("用户请求的地址为："+uri+" ip地址为："+addr);

    }

    @After("execution(* cn.wyu.aop.controller..*.*(..))")
    public void after(JoinPoint joinPoint) {
        log.info("======doAfter方法进入=========");

        //获取签名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        System.out.println("方法执行完了："+method);

    }

    @AfterReturning(pointcut = "execution(* cn.wyu.aop.controller..*.*(..))",returning = "result")
    public void afterReturning(JoinPoint joinPoint,Object result) {
        log.info("======afterReturning方法进入=========");

        //获取签名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        System.out.println("方法执行完毕，返回值类行为："+method + " "+result);


    }

    @AfterThrowing(pointcut = "execution(* cn.wyu.aop.controller..*.*(..))",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Throwable ex) {
        log.info("======afterThrowing方法进入=========");

        //获取签名
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        System.out.println("方法执行出错，异常为："+method + " "+ex);


    }
}

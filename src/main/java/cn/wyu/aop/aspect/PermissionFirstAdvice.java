package cn.wyu.aop.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
//数值越小优先级越高
@Order(1)
public class PermissionFirstAdvice {
    @Pointcut("@annotation(cn.wyu.aop.myAnnotation.PermissionAnnotations)")
    public void permissionCheck(){}

    @Around("permissionCheck()")
    public Object permissionCheckAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("第一个切面");
        Object[] joinPointArgs = joinPoint.getArgs();
        Long id = ((JSONObject) joinPointArgs[0]).getLong("id");
        String name = ((JSONObject) joinPointArgs[0]).getString("name");
        System.out.println("id = "+id);
        System.out.println("name = "+name);

        System.out.println(">>>>>>>>>>>>>>>");
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        System.out.println("目标对象是："+target+" 环绕方法是: "+method);

        /*// id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",8);
        jsonObject.put("name","list");
        joinPointArgs[0] = jsonObject;
        return joinPoint.proceed(joinPointArgs);
    }

}

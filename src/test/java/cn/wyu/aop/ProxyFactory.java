package cn.wyu.aop;

/**
 * ProxFactory用来生成代理对象
 * 它需要所有的参数：目标对象，增强，
 * Created by Yifan Jia on 2018/6/5.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1、创建代理工厂
 * 2、给工厂设置目标对象、前置增强、后置增强
 * 3、调用creatProxy()得到代理对象
 * 4、执行代理对象方法时，先执行前置增强，然后是目标方法，最后是后置增强
 */
//其实在Spring中的AOP的动态代理实现的一个织入器也是叫做ProxyFactory
public class ProxyFactory {
    private Object targetObject;    //目标对象
    private BeforeAdvice beforeAdvice;     //前置增强
    private AfterAdvice afterAdvice;       //后置增强

    /**
     * 生成目标对象
     * @return
     */
    public Object createProxy() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //前置增强
                beforeAdvice.before();
                //执行目标对象的方法
                Object result = method.invoke(targetObject, args);
                //执行后置增强
                afterAdvice.after();
                return result;
            }
        };

        /**
         * 得到代理对象
         */
        Object object = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return object;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public BeforeAdvice getBeforeAdvice() {
        return beforeAdvice;
    }

    public void setBeforeAdvice(BeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    public AfterAdvice getAfterAdvice() {
        return afterAdvice;
    }

    public void setAfterAdvice(AfterAdvice afterAdvice) {
        this.afterAdvice = afterAdvice;
    }
}

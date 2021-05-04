package cn.wyu.test;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 三个参数
 * 1、ClassLoader
 * 方法需要动态生成一个类，这个类实现了A和B两个接口，然后创建这个类的对象
 * 需要生成一个类，这个类也需要加载到方法区中，所以我们需要一个ClassLoader来加载该类
 *
 * 2、Class[] interfaces
 * 我们需要代理对象实现的数组
 *
 * 3、InvocationHandler
 * 调用处理器
 */
public class AgencyTest {
    @Test
    public void test1() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("你好动态代理！");
                return null;
            }
        };
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{A.class, B.class}, invocationHandler);
        A a = (A)proxy;
        B b = (B)proxy;
        a.a();
        b.b();
    }

    @Test
    public void test2() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("你好，我是李焕英！");
                return "Hello world";
            }
        };
        Object proxy = Proxy.newProxyInstance(classLoader,new Class[]{A.class,B.class},invocationHandler);
        A a = (A)proxy;
        B b = (B)proxy;
        Object obj = a.aaa("Hello",100);
        System.out.println(proxy.getClass());
        System.out.println(obj);

    }
}

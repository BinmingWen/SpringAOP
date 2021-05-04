package cn.wyu.aop;


import org.junit.Test;

public class AopMainTest {
    @Test
    public void testAop() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetObject(new ManWaiter());
        proxyFactory.setBeforeAdvice(new BeforeAdvice() {
            @Override
            public void before() {
                System.out.println("你好，欢迎光临");
            }
        });
        proxyFactory.setAfterAdvice(new AfterAdvice() {
            @Override
            public void after() {
                System.out.println("欢迎下次光临");
            }
        });
        Waiter proxy = (Waiter)proxyFactory.createProxy();
        proxy.server();
    }
}

package cn.wyu.aop;

public class ManWaiter implements Waiter{
    @Override
    public void server() {
        System.out.println("服务中。。。。。。。。。");
    }
}

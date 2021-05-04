package cn.wyu.aop.agency;

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

public class AgencyMainTest {



}

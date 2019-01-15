public class SynchronizedMethodLazySingleton {
    private SynchronizedMethodLazySingleton()
    {

    }
    private static SynchronizedMethodLazySingleton synchronizedMethodLazySingleton;

    public synchronized SynchronizedMethodLazySingleton  getInstance()//这种同步方法实现的懒汉式和同步代码块实现的线程安全懒汉式都有一个
            //缺点就是效率太低。
    {
        if(synchronizedMethodLazySingleton==null)
            synchronizedMethodLazySingleton = new SynchronizedMethodLazySingleton();
        return synchronizedMethodLazySingleton;
    }
}

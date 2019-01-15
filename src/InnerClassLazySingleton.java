public class InnerClassLazySingleton {
    private InnerClassLazySingleton()
    {

    }
    //静态内部类虽然被static修饰，但是它不同于static代码块与static方法，不会再外部类进行clinit时随着外部类那些被static修饰的变量与
    //常量被赋值。static内部类会在被使用到的时候才会出发内部类自己的clinit方法。所以这里能够实现了延迟加载，当我们调用到getInstance
    //使用到了Holder类，其中的单例对象instance才会在内部类clinit方法中进行初始化赋值。但是他是怎么实现线程安全的呢？jvm虚拟机会保证
    // 虚拟机会保证一个类的类构造器在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，那么只会有一个线程去执行这个
    // 类的类构造器，其他线程都需要阻塞等待，直到活动线程执行方法完毕。在这种情形下，其他线程虽然会被阻塞，但如果执行类构造器
    // 方法的那条线程退出后，其他线程在唤醒之后不会再次进入/执行类构造器，因为 在同一个类加载器下，一个类型只会被初始化一次，
    // 因此就保证了单例。
    //这是一种高效率的延迟加载单例模式。

    private static class Holder{
        private static InnerClassLazySingleton instance = new InnerClassLazySingleton();
    }

    public static InnerClassLazySingleton getInstance()
    {
        return Holder.instance;
    }
}

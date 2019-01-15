/*
* 懒汉式单例模式。在getinstance会出现线程安全问题。
* 第一个线程判断instance为空时切换到第二个线程创建了单例后在切回来就又创建了一个对象。
* 所以getinstance要用synchronized来同步
* */
public class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton(){

    }
    public static  LazySingleton getInstance()
    {
        synchronized (LazySingleton.class)
        {
            if(instance==null)
                instance = new LazySingleton();
        }

        return instance;
    }

    public static void main(String[] args) {
        LazySingleton lazySingleton1 = LazySingleton.getInstance();
        LazySingleton lazySingleton2 = LazySingleton.getInstance();
        System.out.println(lazySingleton1);
        System.out.println(lazySingleton2);
    }
}

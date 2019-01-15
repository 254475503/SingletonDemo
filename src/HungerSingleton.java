/*
* 饿汉式单例，类加载时就创建唯一的对象。不存在线程安全问题。
* */
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();
    private HungerSingleton(){}
    public static HungerSingleton getInstance()
    {
        return instance;
    }
    public static void main(String[] args) {
        HungerSingleton hungerSingleton1 = HungerSingleton.getInstance();
        HungerSingleton hungerSingleton2 = HungerSingleton.getInstance();
        System.out.println(hungerSingleton1);
        System.out.println(hungerSingleton2);
    }
}



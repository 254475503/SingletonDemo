public class ThreadLocalDoubleCheck {
    //强行秀技术型的使用threadlocal来doublecheck。
    //ThreadLocal可以把他理解为每个线程都持有的，且都不一样的一个局部变量。比如说线程1访问这里的threadlocal和线程2线程3访问的都不一样。
    //他是怎么实现的呢？Thread类中有一个ThreadLocal.ThreadLocalMap threadLocals = null;这个threadLocals是一个map这个map保存了
    //这个线程对象有关的所有ThreadLocal对象与其对应的值。在这个map中，ThreadLocal对象为key，ThreadLocal对象对应的值为value。比如
    //再这个例子中线程1访问getInstance方法，再第一次碰见了threadLocalDoubleCheckThreadLocal.get()，她就会发现自己的threadLocals==null
    //他就会根据this(threadLocalDoubleCheckThreadLocal)和value(null)去创建一个map。然后再判断instance==null 那就instance = new ThreadLocalDoubleCheck();
    //最后调用threadLocalDoubleCheckThreadLocal.set(instance);此时依然以this(threadLocalDoubleCheckThreadLocal)为key instance为value
    //想本线程的threadLocals中加入一个键值对。
    //其实本例子中十个线程里面的threadlocals里面装的都是一样的= = 这里只是用来判断线程有没有进入过这个同步代码块，判断进入过了，之后就
    //不再进入同步代码块了。一般来说每个线程的threadLocals里面相同的key对应的value都应该是设置为不同的。用以区分每个线程。但是具体
    //用法具体分析嘛。
    //其实这个例子写的我很不喜欢，感觉就是强行秀一波ThreadLocal，不过借这个机会了解一下也挺好的。为什么不喜欢这个例子，其实这个例子
    //的效率比之前还要略微低下一点。不仅每个线程多维护了一个threadLocals，而且十个线程就多了十次threadLocalDoubleCheckThreadLocal.set 和get。
    //而且并没有让check的次数变少。所以没有特殊需要，完全是没必要再单例模式下引入ThreadLocal。
    private static ThreadLocalDoubleCheck instance ;
    private static ThreadLocal<ThreadLocalDoubleCheck> threadLocalDoubleCheckThreadLocal = new ThreadLocal<ThreadLocalDoubleCheck>();
    private ThreadLocalDoubleCheck()
    {}
    public static ThreadLocalDoubleCheck getInstace()
    {
        if(threadLocalDoubleCheckThreadLocal.get()==null)
        {
            synchronized (ThreadLocalDoubleCheck.class)
            {
                if(instance == null)
                {
                    instance = new ThreadLocalDoubleCheck();
                }
            }
            threadLocalDoubleCheckThreadLocal.set(instance);
        }
        return instance;
    }

    public static void main(String[] args) {

        Thread[] threads = new Thread[10];
        for(int i = 0;i<10; i++)
        {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(ThreadLocalDoubleCheck.getInstace().hashCode());
                }
            });
            threads[i].start();
        }
    }
}

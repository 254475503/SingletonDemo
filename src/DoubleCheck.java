public class DoubleCheck {
    private DoubleCheck(){}
    private static volatile DoubleCheck instance;
    /*
    * 为什么饿汉式单利模式不仅需要doublecheck同时还需要volatile关键字？我们直到volatile关键字一共有两个功能。
    * 一个是禁止指令重排序，第二个是令多线程共享都能访问到的临界资源对每个线程都保证实时课件性。也就是每次这个变量改变
    * 都会立刻将这个变量的改变从线程自己的高速缓存中放入内存中。让其他线程从内存中能拿到最新的值。然而说了这么一堆，这里用到
    * 的是第一个指令重排序功能。这里我们就要从新建一个对象的过程来看了。jvm新建(new)一个对象的过程一共分三步走。
    * 1.memory = allocate();为对象预先划分内存空间。2 ctorInstance(memory)；创建对象。3instance = memory将对象指向刚分配的空间。
    * 从此我们可以看出，new一个对象并不是一个原子性的操作。而且，再jvm中这个过程是可以被重排序的。这样就会又线程安全问题了。
    * 正常的顺序是123，没有任何问题。但是如果被重排序成132呢？我们分两个线程一步一步的分析来看。
    * 1 线程1进入getinstance
    * 2判断均为null，进入new 操作。此时假设new操作重排序为132.执行完new的1，3操作突然cpu进行线程切换，切换到2
    * 3此时我们来看这个时候的instance的状态。instance已经完成了空间的分配，也已经吧对象指向了分配好的内存。那么此时线程2再去判断
    * instance==null得到的结果是什么呢?显而易见，这个变量已经指向了分配好的内存空间。所以不是null
    * 4线程2判断结束，返回instance。那么问题来了。我们new操作中的2没做，也就是创建对象。那么线程2此时返回的就是一个未创建完成
    * 的不正常的对象。
    * 这就是我们需要volatile的原因。他修饰instance以后，new操作的重排序就会被禁止。就不会出现这种重排序问题了
    * */
    public static DoubleCheck getInstance()
    {
        if(instance == null)//关于为什么需要doublechecke，首先我们第一次创建instance时需要synchronize是毫无疑问的。不然多个线程
            //进入了内层的if那就会创建多个对象了。那么我们为什么需要还需要这个外层的if判断呢？就是因为效率问题。如果没有
            //外层的判断，每次进入getInstance不管三七二十一就synchronized，回极大的拖慢程序的运行速度。所以我们再进行创建实例之前
            //就判断instance是否为空，为空则synchronized不为空就直接返回了。这样保证了synchronized只会执行一次。减少了synchronized
            //带来的时间成本
            
        {
            synchronized (DoubleCheck.class){
                if(instance == null)

                    instance = new DoubleCheck();
            }


        }
        return instance;
    }
}

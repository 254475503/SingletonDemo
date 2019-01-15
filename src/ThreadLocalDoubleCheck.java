public class ThreadLocalDoubleCheck {
    //ǿ���㼼���͵�ʹ��threadlocal��doublecheck��
    //ThreadLocal���԰������Ϊÿ���̶߳����еģ��Ҷ���һ����һ���ֲ�����������˵�߳�1���������threadlocal���߳�2�߳�3���ʵĶ���һ����
    //������ôʵ�ֵ��أ�Thread������һ��ThreadLocal.ThreadLocalMap threadLocals = null;���threadLocals��һ��map���map������
    //����̶߳����йص�����ThreadLocal���������Ӧ��ֵ�������map�У�ThreadLocal����Ϊkey��ThreadLocal�����Ӧ��ֵΪvalue������
    //������������߳�1����getInstance�������ٵ�һ��������threadLocalDoubleCheckThreadLocal.get()�����ͻᷢ���Լ���threadLocals==null
    //���ͻ����this(threadLocalDoubleCheckThreadLocal)��value(null)ȥ����һ��map��Ȼ�����ж�instance==null �Ǿ�instance = new ThreadLocalDoubleCheck();
    //������threadLocalDoubleCheckThreadLocal.set(instance);��ʱ��Ȼ��this(threadLocalDoubleCheckThreadLocal)Ϊkey instanceΪvalue
    //�뱾�̵߳�threadLocals�м���һ����ֵ�ԡ�
    //��ʵ��������ʮ���߳������threadlocals����װ�Ķ���һ����= = ����ֻ�������ж��߳���û�н�������ͬ������飬�жϽ�����ˣ�֮���
    //���ٽ���ͬ��������ˡ�һ����˵ÿ���̵߳�threadLocals������ͬ��key��Ӧ��value��Ӧ��������Ϊ��ͬ�ġ���������ÿ���̡߳����Ǿ���
    //�÷���������
    //��ʵ�������д���Һܲ�ϲ�����о�����ǿ����һ��ThreadLocal����������������˽�һ��Ҳͦ�õġ�Ϊʲô��ϲ��������ӣ���ʵ�������
    //��Ч�ʱ�֮ǰ��Ҫ��΢����һ�㡣����ÿ���̶߳�ά����һ��threadLocals������ʮ���߳̾Ͷ���ʮ��threadLocalDoubleCheckThreadLocal.set ��get��
    //���Ҳ�û����check�Ĵ������١�����û��������Ҫ����ȫ��û��Ҫ�ٵ���ģʽ������ThreadLocal��
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

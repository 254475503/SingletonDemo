public class SynchronizedMethodLazySingleton {
    private SynchronizedMethodLazySingleton()
    {

    }
    private static SynchronizedMethodLazySingleton synchronizedMethodLazySingleton;

    public synchronized SynchronizedMethodLazySingleton  getInstance()//����ͬ������ʵ�ֵ�����ʽ��ͬ�������ʵ�ֵ��̰߳�ȫ����ʽ����һ��
            //ȱ�����Ч��̫�͡�
    {
        if(synchronizedMethodLazySingleton==null)
            synchronizedMethodLazySingleton = new SynchronizedMethodLazySingleton();
        return synchronizedMethodLazySingleton;
    }
}

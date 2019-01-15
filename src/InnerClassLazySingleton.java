public class InnerClassLazySingleton {
    private InnerClassLazySingleton()
    {

    }
    //��̬�ڲ�����Ȼ��static���Σ���������ͬ��static�������static�������������ⲿ�����clinitʱ�����ⲿ����Щ��static���εı�����
    //��������ֵ��static�ڲ�����ڱ�ʹ�õ���ʱ��Ż�����ڲ����Լ���clinit���������������ܹ�ʵ�����ӳټ��أ������ǵ��õ�getInstance
    //ʹ�õ���Holder�࣬���еĵ�������instance�Ż����ڲ���clinit�����н��г�ʼ����ֵ������������ôʵ���̰߳�ȫ���أ�jvm������ᱣ֤
    // ������ᱣ֤һ������๹�����ڶ��̻߳����б���ȷ�ļ�����ͬ�����������߳�ͬʱȥ��ʼ��һ���࣬��ôֻ����һ���߳�ȥִ�����
    // ����๹�����������̶߳���Ҫ�����ȴ���ֱ����߳�ִ�з�����ϡ������������£������߳���Ȼ�ᱻ�����������ִ���๹����
    // �����������߳��˳��������߳��ڻ���֮�󲻻��ٴν���/ִ���๹��������Ϊ ��ͬһ����������£�һ������ֻ�ᱻ��ʼ��һ�Σ�
    // ��˾ͱ�֤�˵�����
    //����һ�ָ�Ч�ʵ��ӳټ��ص���ģʽ��

    private static class Holder{
        private static InnerClassLazySingleton instance = new InnerClassLazySingleton();
    }

    public static InnerClassLazySingleton getInstance()
    {
        return Holder.instance;
    }
}

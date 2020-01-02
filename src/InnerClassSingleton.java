public class InnerClassSingleton {

    private InnerClassSingleton()
    {
        if(InnerClass.instance!=null)
            throw  new RuntimeException("不允许创建多个实例");
    }
    public InnerClassSingleton getInstance()
    {
        return InnerClass.instance;
    }
    private static class InnerClass{
        private static final InnerClassSingleton instance = new InnerClassSingleton();

    }
}

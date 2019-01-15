import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;

public enum EnumSingleton {
    SINGLETON;
    private String name;
    private String ID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public static void main(String[] args) {
        EnumSingleton.SINGLETON.getID();
    }
    AtomicInteger
}

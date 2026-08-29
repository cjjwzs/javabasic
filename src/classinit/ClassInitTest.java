package classinit;

public class ClassInitTest {

    public ClassInitTest() {
        super();
    }

    public static String myName = "LiMing";

    static {
        System.out.println("ClassInitTest");
    }

    public static void initmethod1() {
        System.out.println("ClassInitTest.method1");
    }
}

class SubClassInitTest extends ClassInitTest {

    public static final int a = 2 * 3;

    static {
        System.out.println("SubClassInitTest");
    }

    public SubClassInitTest() {
        super();
    }
}

class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //1 Class.forName加载类导致类初始化
        //Class.forName("classinit.ClassInitTest", true, ClassLoader.getSystemClassLoader());
        //2 主动使用类的静态变量导致类初始化
        //System.out.printf("", ClassInitTest.myName);
        //3 新建子类会初始化父类
        //new SubClassInitTest();
        //4 子类访问父类的静态变量，只会初始化父类的静态变量，子类不进行初始化
        // System.out.printf("打印静态变量 %s", SubClassInitTest.myName);
        //6 对于final类型的静态变量，如果在编译时就能计算出变量的取
        //值，那么这种变量被看作编译时常量。Java程序中对类的编译时常量
        //的使用，被看作是对类的被动使用，不会导致类的初始化
        //System.out.printf("final 变量  %s", SubClassInitTest.a);
        SubClassInitTest.initmethod1();
    }
}

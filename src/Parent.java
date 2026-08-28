import java.util.Random;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-16 09:14
 **/

class Parent {
    // 1. 静态变量（按书写顺序）
    static String parentStaticVar = "1.父类静态变量赋值";

    // 2. 静态初始化块
    static {
        System.out.println("2.父类静态初始化块");
    }

    // 3. 实例变量
    String parentInstanceVar = "3.父类实例变量赋值";

    // 4. 实例初始化块
    {
        System.out.println("4.父类实例初始化块");
    }

    // 5. 构造方法
    Parent() {
        System.out.println("5.父类构造方法");
    }
}

class Child extends Parent {
    // 6. 子类静态变量
    static String childStaticVar = "6.子类静态变量赋值";

    // 7. 子类静态初始化块
    static {
        System.out.println("7.子类静态初始化块");
    }

    // 8. 子类实例变量
    String childInstanceVar = "8.子类实例变量赋值";

    // 9. 子类实例初始化块
    {
        System.out.println("9.子类实例初始化块");
    }

    // 10. 子类构造方法
    Child() {
        // 这里编译器会隐式插入 super()
        System.out.println("10.子类构造方法");
    }
}
enum Color { RED, GREEN, BLUE }
class InitOrderTest {
    public static void main(String[] args) {
        System.out.println("===== 开始创建第一个 Child 对象 =====");
        Child c1 = new Child();
        System.out.println("===== 创建完毕 =====\n");

        // 再创建一个，观察静态块是否还会执行
        System.out.println("===== 开始创建第二个 Child 对象 =====");
        Child c2 = new Child();
        System.out.println("===== 创建完毕 =====");
    }
}
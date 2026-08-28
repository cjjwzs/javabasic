package init;

class Parent {
    // 非静态变量初始化
    int a = initA();

    // 实例初始化块
    {
        System.out.println("Parent 实例初始化块执行，此时 a = " + a);
        a = 20;  // 修改 a 的值
    }

    // 构造方法
    Parent() {
        System.out.println("Parent 构造方法执行，此时 a = " + a);
        a = 30;
    }

    private int initA() {
        System.out.println("Parent 非静态变量 a 初始化，返回 10");
        return 10;
    }

    public void show() {                     // 实例方法
        System.out.println("Parent show");
    }
}

class Child extends Parent {
    int b = initB();

    {
        System.out.println("Child 实例初始化块执行，b = " + b);
    }

    Child() {
        System.out.println("Child 构造方法执行");
    }

    private int initB() {
        System.out.println("Child 非静态变量 b 初始化，返回 100");
        return 100;
    }

    public void show() throws RuntimeException {
        System.out.println("Child show");
    }

    protected void method(int v) {
    }

    private void method(String s) {
    } //重载
}

class TestInitBlock {
    public static void main(String[] args) {
        new Child();
    }
}
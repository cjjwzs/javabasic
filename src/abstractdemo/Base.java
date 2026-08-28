package abstractdemo;

import static java.lang.System.out;


public abstract class Base {

    static int a;
    String b;

    public Base() {
        super();
    }

    public abstract void method1();

    public abstract void method2();

    public static void test() {
        System.out.println("test");
        //out.printf("", b); // Non-static field 'b' cannot be referenced from a static context
        out.printf("", a);
    }

} //Base类是抽象类


class sub extends Base {
    public sub() {
    }

    public void method1() {
        out.println("sub method1");
    }

    @Override
    public void method2() {

    }

    private static abstract class Tester {
        private String name;

        Tester(String name) {
            this.name = name;
        }
    }
}

class MyName {
    private String name;

    public MyName() {
        super();
    }

    public String getName() {
        final String age = "20";
        return name;
    }
}

class Sub extends Base {
    public Sub() {
        super();
    }

    @Override
    public void method1() {
        out.println("sub method1");
    }

    @Override
    public void method2() {
        out.println("sub method2");
    }

    //Sub类是具体类
    public static void main(String[] args) {
        //Base base1 = new Base(); //编译出错，不能创建抽象类Base的实例
        Base base2 = new Sub(); //合法，可以创建具体类 Sub的实例
    }
}

interface A {
    int var1 = 0; //编译出错，var1变量被看作静态常量，必须被显式初始

    int var2 = 0; //编译出错，var1变量必须是 public类型

    int var3 = 3; //合法,var3变量默认为 public、static、final

    static void method() {
        out.println("method");
    }

    default void defaultMethod() {
        out.println("defaultMethod");
    }
}

class Base1 extends Sub {
    static int i = 5;

    static { //第一个静态代码块
        out.println(" First Static code i= " + i++);
    }

    { //第一个静态代码块
        out.println(" Code Block  i= " + i++);
    }

    static { //第一个静态代码块
        out.println(" Second Static code i= " + i++);
        out.println("static import ");
    }

    public Base1() {
    }

    public static void main(String[] args) {
        new Base1();
        A.method();

    }
}


class BaseException extends Exception {
    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    protected BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
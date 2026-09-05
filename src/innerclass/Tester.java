package innerclass;

/**
 * 成员内部类 包含 实列内部类 静态内部类
 * 局部内部类
 */
class OuterExample {

    private String myName = "LiPing";
    int v = 1;
    private Integer age = 20;

    public static String staticVar = "staticVar";

    private InnerExample tool = new InnerExample();

    //class OuterExample {} //编译错误，不允许内部类与外部类重名

    /**
     * 静态内部类
     */
    public static class InnerStatic {
        public InnerStatic() {
        }

        public void foo() {
            /**
             * 静态内部类可以访问外部类静态变量
             */
            System.out.println(staticVar);
            /**
             * Non-static field 'age' cannot be referenced from a static context
             * 静态内部类不能直接访问外部类非静态变量
             */
            //System.out.println(age);
            /**
             * 可以通过外部类的实列进行访问
             */
            OuterExample outerExample = new OuterExample();
            System.out.println(outerExample.age);
        }
    }

    /**
     * 成员实列内部类
     */
    public class InnerExample {
        private String inner1 = "inner1";
        int v = 2;
        public static final String finalStaticVar = "finalStaticVar";

        //在实例内部类中不能定义静态成员,实例内部类中只能定义实例成员。
        //Static declarations in inner classes are not supported at language level '8'
        //static String inner2 = "inner2";
        public void foo() {
            System.out.println("InnerExample ");
            System.out.println("foo");
            System.out.println(myName);
            System.out.println("this.v " + this.v);
            System.out.println("OuterExample.this.v " + OuterExample.this.v);
            System.out.println(age);
            System.out.println(staticVar);
        }

        //static class C {} 实列内部类里面不能生命静态内部类
    }

    public void foo() {
        System.out.println("OuterExample ");
        tool.foo();
        InnerExample innerExample = new InnerExample();
        System.out.println(innerExample.inner1);
        //class OuterExample {} //编译错误，不允许内部类与外部类重名

    }
}

public class Tester {
    public static void main(String[] args) {
        OuterExample example = new OuterExample();
        example.foo();

        OuterExample.InnerExample innerExample = new OuterExample().new InnerExample();
        innerExample.foo();
    }
}
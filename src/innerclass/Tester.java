package innerclass;

/**
 * 成员内部类 包含 实列内部类 静态内部类
 * 局部内部类
 */
class OuterExample {

    private String myName = "LiPing";
    int v = 1 ;
    private Integer age = 20;

    private InnerExample tool = new InnerExample();

    //class OuterExample {} //编译错误，不允许内部类与外部类重名

    /**
     * 成员内部类
     */
    public class InnerExample {
        private String inner1 = "inner1";
        int v = 2;
        //在实例内部类中不能定义静态成员,实例内部类中只能定义实例成员。
        //static String inner2 = "inner2"; Static declarations in inner classes are not supported at language level '8'

        public void foo() {
            System.out.println("InnerExample ");
            System.out.println("foo");
            System.out.println(myName);
            System.out.println("this.v "+this.v);
            System.out.println("OuterExample.this.v "+ OuterExample.this.v);
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
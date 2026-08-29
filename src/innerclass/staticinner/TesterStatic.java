package innerclass.staticinner;

import jdk.nashorn.internal.ir.CallNode;

class A {
    String myName = "myName";
    static Integer age = 20;

    public static class StaticB {
        public int v = 2;
        int myAge = age;
        static String hello = "";
        /**
         * 静态内部类可以直接访问外部类的静态成员，如果访问外部类的实
         * 例成员，必须通过外部类的实例去访问
         */
        //String staticName = myName;
        String myNameNow = new A().myName;

        public static class StaitcC {
            public String MyName = "myName";
            public static String myAge1 = hello;
        }

    }
}

public class TesterStatic {
    public static void main(String[] args) {
        A.StaticB b = new A.StaticB();
        System.out.printf("%s", b.v);
        A a = new A();
        System.out.println(a.myName);
    }
}

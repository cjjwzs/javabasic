package innerclass;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-18 17:32
 **/

public class A {
    public String hello = "hello" ;
    private int a1; //实例变量 a1
    private static int a2; //静态变量 a2

    /**
     * 静态内部类
     */
    static class B {
        public String name = "innerB" ;
        int v1;
        static int v2;
        public void method(){
            System.out.println("method");
        }

        public static class C{
            static int v3;
            int v4;
        }
    }
    /**
     * 静态内部类
     */
    static class CC {
        public static String name = "innerC" ;
        public String anotherName = "anotherC" ;

        int v1; //实例变量
        static int v2; //静态变量

        public void method(){
            System.out.println("method" + this.anotherName);
            System.out.println("methodC");
            //int b1 = a1; //编译错误，不能直接访问外部类A的实例变量 a1
            int b2=a2; //合法，可以直接访问外部类A的静态变量 a2
            int b3=new A().a1; //合法，可以通过类A的实例访问变量 a1

            class B{
                //static int v1; //编译错误
                int v2; //合法
               // static class C{ //编译错误
                 //   int v3;
                //}
                class D{
                    int v3;
                    public void print(){
                        System.out.println("print");
                        new B(){
                            public void print(){
                                System.out.println("print ");
                            }
                        };
                    }
               }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        B cc = new B(){
            public void print(){
                System.out.println("print");
            }
        };

        B ccc = new B(){
            public void print(){
                System.out.println("print");
            }
        };

        B b = new A.B();
        b.method();

        A.B.C c=new A.B.C();
        b.v1=1;
        b.v2=1;
        //A.B.v1=1 ; //编译错误
        A.B.v2=1; //合法
        A.B.C.v3=1; //合法

        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                System.out.println("thread end.");
            }
        };
        t.start();
        t.join();
        System.out.println("main end...");
    }
}
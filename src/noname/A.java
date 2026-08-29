package noname;

public class A {
    A(int v) {
        System.out.println("another constructor");
    }

    A() {
        System.out.println("default constructor");
    }

    void method() {
        System.out.println("from A");
    }

    public static void main(String[] args) {
//        new A().method();
//        A a = new A(){
//            @Override
//            void method() {
//                System.out.println("匿名类内部方法调用");
//            }
//        };
//        a.method();

        int b = 1;

        A a1 = new A(b){

            {System.out.println("initialize instance");}

            @Override
            void method() {
                System.out.println("from anonymous" + b);
            }
        };
        a1.method();

    }
}

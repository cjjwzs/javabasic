package innerclass.defautmethod;

interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
public class C implements A, B {
    public static void main(String... args) {
        new C().hello();
    }
}

class D implements A{ }

//class D implements A {
//    @Override
//    public void hello() {
//        System.out.println("Hello from D");
//    }
//}

class C1 extends D implements B, A {
    public static void main(String... args) {
        new C1().hello();
    }
}

class C2 implements B, A {
    public static void main(String... args) {
        new C2().hello();
    }
}



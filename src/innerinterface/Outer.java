package innerinterface;

public class Outer {
    // 1. 定义了一个 public 的嵌套接口（默认就是 static 的）
    public interface PublicNestedInterface {
        public abstract void doSomething();

        static void staticMethd() {
            System.out.println("static methd");
        }
    }

    // 2. 定义了一个 private 的嵌套接口（仅外部类内部可见）
    private interface PrivateNestedInterface {
        void doSecretWork();
    }

    // 3. 外部类可以直接使用这个接口
    public void execute(PublicNestedInterface impl) {
        impl.doSomething();
    }

    // 1. 定义私有嵌套接口：规定内部降温策略的契约
    // 外部世界不需要知道，也不允许直接实现这个接口
    private interface CoolingStrategy {
        void coolDown();
    }

    // 2. 外部类内部的具体实现
    private class InverterCooling implements CoolingStrategy {
        @Override
        public void coolDown() {
            System.out.println("正在使用变频技术降温...");
        }
    }

}

// 4. 在外部类之外，可以通过 "外部类名.接口名" 来访问
class Test implements Outer.PublicNestedInterface {
    @Override
    public void doSomething() {
        System.out.println("实现了嵌套接口");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.doSomething();
//        test.staticMethd();
        Outer.PublicNestedInterface.staticMethd();
    }
}
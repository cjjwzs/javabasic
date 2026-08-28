package init;

public class Base {
    public Base() {
        method();
    }

    public void method() {
    }
}

class Sub extends Base {
    private String str = null;

    public Sub() {
        str = "1234";
    }

    public void method() {
        System.out.println(str.length());
    } //覆盖Base类的method()方法

    public static void main(String args[]) {
        Sub sub = new Sub(); //抛出NullPointerException
        sub.method();
    }
}
package innerclass;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-19 08:37
 **/

class Outer1 {
    private int a;

    public Outer1(int a) {
        this.a = a;
    }

    class Inner {
        public Inner() {
        }

        public void print() {
            System.out.println("a=" + a);
        } //访问外部

        //类的实例变量 a
    }
}

public class Sample extends Outer1.Inner {
    //public Sample(){} //编译错误
    public Sample(Outer1 o) {
        o.super();
    }

    public static void main(String args[]) {
        Outer1 outer1 = new Outer1(1);
        Outer1 outer2 = new Outer1(2);
        Outer1.Inner in = outer1.new Inner();
        in.print(); //打印 a=1
        Sample s1 = new Sample(outer1);
        Sample s2 = new Sample(outer2);
        s1.print(); //打印 a=1
        s2.print(); //打印 a=2

    }
}
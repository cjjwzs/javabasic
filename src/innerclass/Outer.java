package innerclass;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-19 11:09
 **/

public class Outer {
    Inner in;

    Outer() {
        in = new Inner();
    } //构造Outer.Inner类的实例

    public class Inner { //public访问级别
        public Inner() {
            System.out.println("inner of Outer");
        }
    }
}

class SubOuter extends Outer {

    public static interface Tool {
        public int add(int a, int b);
    }

    //静态内部接口
    private Tool tool = new Tool() {
        public int add(int a, int b) {
            return a + b;
        }
    }; //匿名类

    SubOuter() {
        super();
    }


    //    public void method(){
//        System.out.println("sub method");
//    }
    class Inner { //默认访问级别
        public Inner() {
            System.out.println("inner of SubOuter");
        }
    }

    class MyTool implements SubOuter.Tool {
        public int add(int a, int b) {
            int result = a + b;
            System.out.println(result);
            return result;
        }
    }

    public static void main(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SubOuter.Inner in1 = new SubOuter().new Inner();
        Outer.Inner in2 = new Outer().new Inner();

        SubOuter sub = new SubOuter() {
            public void methodA() {
                System.out.println("sub methodA");
            }

            ;
        };
        // sub.method();
        sub.getClass().getMethod("methodA").invoke(sub);

        new SubOuter() {
            public void method() {
                System.out.println("sub method");
            }
        }.method();

        SubOuter.MyTool myTool = sub.new MyTool();
        System.out.println(myTool.add(1, 2));
    }
}
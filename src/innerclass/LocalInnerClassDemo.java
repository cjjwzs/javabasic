package innerclass;

/**
 * 局部内部类
 */
public class LocalInnerClassDemo {
    public String name = "Liping" ;
    public void testMethod(int methodParam) {
        // 1. 普通局部变量
        int localVar = 10;

        // 2. 实际上的最终变量（Effectively Final）
        // 虽然没有写 final，但后面没有修改过它的值，Java 8+ 允许内部类访问
        int effectivelyFinalVar = 20;

        // 3. 非最终变量
        int changeableVar = 30;

        // 局部内部类
        class MyLocalInner {
            public void show() {
                System.out.println("访问方法参数：" + methodParam);
                System.out.println("访问局部变量：" + localVar);
                System.out.println("访问实际上的最终变量：" + effectivelyFinalVar);
                LocalInnerClassDemo localInnerClassDemo = new LocalInnerClassDemo();
                System.out.println(localInnerClassDemo.name);
                //System.out.println(changeableVar);
                // ❌ 编译报错！Cannot refer to the non-final local variable changeableVar
                // 因为 changeableVar 在后面被修改了，不再是“实际上的 final”
            }
        }

        // 修改了 changeableVar，所以它失去了“实际上的 final”资格
        changeableVar = 40;

        // 如果尝试在这里修改 effectivelyFinalVar，也会报错
        // effectivelyFinalVar = 50; // ❌ 编译报错

        // 创建局部内部类对象并调用方法
        MyLocalInner inner = new MyLocalInner();
        inner.show();
    }

    public static void main(String[] args) {
        LocalInnerClassDemo demo = new LocalInnerClassDemo();
        demo.testMethod(100);
    }
}
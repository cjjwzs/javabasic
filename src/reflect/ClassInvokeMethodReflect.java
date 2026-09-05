package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassInvokeMethodReflect {
    public static void testInvokeStringMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("testInvokeStringMethod");
        // String对象:
        String s = "Hello world";
        // 获取String substring(int)方法，参数为int:
        Method m = String.class.getMethod("substring", int.class);
        // 在s对象上调用该方法并获取结果:
        String r = (String) m.invoke(s, 6);
        // 打印调用结果:
        System.out.println(r); // "world"
    }

    public static void testInvokeStaticMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("testInvokeStaticMethod");
        //获取Integer.parseInt(String)方法，参数为String:
        Method m = Integer.class.getMethod("parseInt", String.class);
        // 调用该静态方法并获取结果:
        // 第一个参数 obj为null：要调用方法的对象实例。静态方法不属于对象，传 `null`！
        Integer n = (Integer) m.invoke(null, "12345");
        // 打印调用结果:
        System.out.println(n);
    }

    public static void testInvokeInstanceMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        System.out.println("testMethod3");
        Person p = new Person();
        Method m = p.getClass().getDeclaredMethod("setName", String.class);
        m.setAccessible(true);
        m.invoke(p, "Bob");
        System.out.println(p.name);
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        testInvokeStringMethod();
        testInvokeStaticMethod();
        testInvokeInstanceMethod();
    }

}

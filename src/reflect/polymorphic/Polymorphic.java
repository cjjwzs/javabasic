package reflect.polymorphic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 运行代码，发现打印出的是 Student:hello ，因此，使用反射调用方法时，仍然遵循多态原
 * 则：即总是调用实际类型的覆写方法（如果存在）。上述的反射代码：
 * Method m = Person.class.getMethod("hello");
 * m.invoke(new Student());
 * 实际上相当于：
 * Person p = new Student();
 * p.hello();
 */
public class Polymorphic {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 获取Person的hello方法:
        Method h = Person.class.getMethod("hello");
        // 对Student实例调用hello方法:
        h.invoke(new Student());
    }
}

class Person {
    public void hello() {
        System.out.println("Person:hello");
    }
}

class Student extends Person {
    @Override
    public void hello() {
        System.out.println("Student:hello");
    }
}
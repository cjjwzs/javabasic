package reflect.polymorphic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
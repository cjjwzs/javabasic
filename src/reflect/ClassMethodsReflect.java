package reflect;

import java.lang.reflect.Method;

public class ClassMethodsReflect {
    public static void main(String[] args) throws NoSuchMethodException {
        Class stdClass = Student.class;
        // 获取public方法getScore，参数为String:
        Method getScore = stdClass.getMethod("getScore", String.class);
        System.out.println(getScore);
        // 获取继承的public方法getName，无参数:
        Method getName = stdClass.getMethod("getName");
        System.out.println(getName);
        // 获取private方法getGrade，参数为int:
        Method getGrade = stdClass.getDeclaredMethod("getGrade",
                int.class);
        System.out.println(getGrade);
    }
}

class Student extends Person {
    public int getScore(String type) {
        return 99;
    }

    private int getGrade(int year) {
        return 1;
    }
}

class Person {
    String name;
    public String getName() {
        return "Person";
    }
    private void setName(String name) {
        this.name = name;
    }
}
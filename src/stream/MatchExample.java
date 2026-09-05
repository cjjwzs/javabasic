package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MatchExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("张三", 18),
                new Student("李四", 22),
                new Student("王五", 20)
        );

        // 判断：有没有年龄大于30的？没有，返回true
        boolean noOver30 = list.stream()
                .noneMatch(s -> s.getAge() > 30);
        System.out.println(noOver30); // true

        // 判断：有没有年龄大于20的？有（李四22），返回false
        boolean noOver20 = list.stream()
                .noneMatch(s -> s.getAge() > 20);
        System.out.println(noOver20); // false

        // 未绑定方法引用：Student::getName
        List<String> names = list.stream()
                //.map(Student::getName)
                .map((student -> {
                    return student.getName();
                }))
                .collect(Collectors.toList());
        names.forEach(System.out::println);
        /**
         * `Student::getName` 本身**没有绑定任何 Student 对象**；
         * 执行时，需要把**对象作为第一个参数传入**，相当于：`对象.getName()`。
         */
        // Function<Student,String>：入参Student对象，返回String
        Function<Student, String> func = Student::getName;
        Student s = new Student("张三", 20);
        String name = func.apply(s); // 必须传入对象s作为调用者
        System.out.println(name); // 张三

    }
}

class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

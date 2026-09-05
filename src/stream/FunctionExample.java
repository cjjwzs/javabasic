package stream;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class FunctionExample {
    public static void main(String[] args) {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        Function<Integer, Integer> i = h.compose(x -> x + 1);
        int result = h.apply(1);
        System.out.println(result);

        int result2 = i.apply(1);
        System.out.println(result2);

        Stream.iterate(0, x -> x + 1).limit(10).forEach(System.out::println);

        // 生成 3 个相同的字符串
        Stream.generate(() -> "Hello Java")
                .limit(3)
                .forEach(System.out::println);
        // 输出: Hello Java, Hello Java, Hello Java

        // 生成 10 个测试用户
        Stream.generate(new PersonSupplier())
                .limit(10)
                .forEach(System.out::println);
    }
}

// 自定义 Supplier，每次生成递增的测试用户
class PersonSupplier implements Supplier<Person> {
    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
        return new Person(index++, "User" + index, random.nextInt(100));
    }
}
class Person {
    private int id;
    private String name;
    private int age;
    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

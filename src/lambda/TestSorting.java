package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-24 11:21
 **/

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

class Person {
    int height;
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
class City{
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
public class TestSorting {

    public static void main(String[] args) {



        final List<Person> people = Arrays.asList(new Person());

        Comparator<Person> byHeight = Comparator.comparing(Person::getHeight);

        final Map<String, Optional<Person>> collect = people.stream().collect(groupingBy(Person::getCity, reducing(BinaryOperator.maxBy(byHeight))));

        List<String> items = Arrays.asList("apple", "banana", "apple", "orange");

        final Optional<String> maxLength = items.stream().max(Comparator.comparingInt(String::length));

        if (maxLength.isPresent()) {
            System.out.println("maxLength : " + maxLength.get());
        }

// 获取所有不重复的水果名称，并用逗号拼接
        String fruitNames = items.stream()
                .distinct() // 去重
                .collect(Collectors.joining(", ")); // 使用 joining 拼接

        System.out.println(fruitNames);
// 输出结果：apple, banana, orange

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );

        pythagoreanTriples.forEach(ele -> System.out.println(ele[0] + " " + ele[1] + " " + ele[2]));

        List<Developer> listDevs = getDevelopers();

        System.out.println("Before Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

        //sort by age
        Collections.sort(listDevs, Comparator.comparingInt(Developer::getAge));

        System.out.println("After Sort");
        for (Developer developer : listDevs) {
            System.out.println(developer);
        }

    }

    private static List<Developer> getDevelopers() {

        List<Developer> result = new ArrayList<>();

        result.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        result.add(new Developer("alvin", new BigDecimal("80000"), 20));
        result.add(new Developer("jason", new BigDecimal("100000"), 10));
        result.add(new Developer("iris", new BigDecimal("170000"), 55));

        return result;

    }

}

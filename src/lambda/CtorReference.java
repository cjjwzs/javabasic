package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-23 10:53
 **/

class Dog {
    String name;
    int age = -1; // For "unknown"

    Dog() {
        name = "stray";
    }

    Dog(String nm) {
        name = nm;
    }

    Dog(String nm, int yrs) {
        name = nm;
        age = yrs;
    }

    public <T> Predicate<T> debug(Predicate<T> predicate, String name) {
        return t -> {
            boolean result = predicate.test(t);
            System.out.println(name + "(" + t + ") = " + result);
            return result;
        };
    }

}

interface MakeNoArgs {
    Dog make();
}

interface Make1Arg {
    Dog make(String nm);
}

interface Make2Args {
    Dog make(String nm, int age);
}


public class CtorReference {
    public static void main(String[] args) {

        Stream.of(1, 2, 3)
                .map(i -> Stream.of("Gonzo", "Kermit", "Beaker"))
                .map(e-> e.getClass().getName())
                .forEach(System.out::println);

        Stream.of(1, 2, 3)
                .flatMap(i -> Stream.of("Gonzo", "Fozzie", "Beaker"))
                .forEach(System.out::println);

        List<String> words = Arrays.asList("hello", "world");

// 目标：提取出所有不重复的字母 [h, e, l, o, w, r, d]
        List<String> uniqueCharacters = words.stream()
                .map(w -> w.split(""))           // 1. map：把每个单词拆成 String[] 数组
                .flatMap(Arrays::stream)         // 2. flatMap：把所有 String[] 数组“拍平”合并成一个字符流
                .distinct()                      // 3. 去重
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);
        List<String> words1 = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);
        MakeNoArgs mna = Dog::new; // [1]
        Make1Arg m1a = Dog::new; // [2]
        Make2Args m2a = Dog::new; // [3]
        Dog dn = mna.make();
        Dog d1 = m1a.make("Comet");
        Dog d2 = m2a.make("Ralph", 4);

        System.out.println(d2.age);

        List<String> list = new ArrayList<>();

        // Consumer 的函数描述符是 (T) -> void，要求不返回任何值
        Consumer<String> consumer = s -> list.add(s);

        Predicate<String> consumer1 = s -> list.add(s);

        System.out.println(consumer1.test("ssss"));

        consumer.accept("Hello"); // 尽管 list.add 返回了 boolean(true)，但这里被合法忽略了


        // Predicate返回了一个boolean
        Predicate<String> p = s -> list.add(s);
        // Consumer返回了一个void
        Consumer<String> b = s -> list.add(s);

        BiPredicate<String, String> startsWith = String::startsWith;
        boolean result = startsWith.test("hello", "he");  // 相当于 "hello".startsWith("he")
        System.out.println("result: " + result);
        // 等价于 Lambda: (s1, s2) -> s1.startsWith(s2)

        Function<String, Integer> parser = Integer::parseInt; // parseInt 是静态方法
        int num = parser.apply("42");  // 结果: 42
        System.out.println("num: " + num);
        // 等价于 Lambda: s -> Integer.parseInt(s)

    }
}
package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-22 16:00
 **/

@FunctionalInterface
interface Function<T, R> {
    R apply(T t);
}

public class Demo {
    // 符合签名的静态方法
    public static Integer strLengthStatic(String s) {
        return s.length();
    }

    // 符合签名的实例方法
    public Integer strLengthInstance(String s) {
        return s.length();
    }

    public static void process(String input, Function<String, Integer> func) {
        Integer result = func.apply(input);
        System.out.println("输入: [" + input + "] -> 结果: " + result);
        System.out.println("--------------------------");
    }

    public static void main(String[] args) {

        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

// 使用 flatMap 将嵌套结构打平
        List<Integer> flatList = nestedList.stream()
                .flatMap(list -> list.stream()) // 将每个子 List 转换为 Stream，然后合并
                .collect(Collectors.toList());

        flatList.forEach(System.out::println);

// 结果：[1, 2, 3, 4, 5, 6]

// 定义一个 BiPredicate，判断两个整数是否都大于 10
        BiPredicate<Integer, Integer> bothGreaterThan10 = (n, m) -> n > 10 && m > 10;

        BiPredicate<String, String> lambdaContains = (str, subStr) -> str.contains(subStr);

        System.out.println(" contains : "+lambdaContains.test("ABC","A"));

        BiPredicate<String, String> lambdaContains1 = String::contains;

        int portNumber = 1337;
        Runnable r = () -> System.out.println(portNumber);
        Thread thread = new Thread(r);
        thread.start();
        System.out.println(bothGreaterThan10.test(12, 15)); // 输出: true
        System.out.println(bothGreaterThan10.test(12, 5));  // 输出: false

        BinaryOperator<Integer> sum = (a, b) -> a + b;

        System.out.println("sum: " + sum.apply(1, 2));

        // 场景1：对整数进行平方运算 (int -> int)
        UnaryOperator<Integer> square = num -> num * num;
        System.out.println("5的平方是：" + square.apply(5)); // 输出：25

        final IntFunction<Integer> integerIntFunction = (int x) -> x + 1;

        System.out.println(integerIntFunction.apply(123));

        Stream<Integer> natual = Stream.generate(new NatualSupplier());
        // 注意：无限序列必须先变成有限序列再打印:
        natual.limit(20).forEach(System.out::println);

        String text = "Hello Java";
        process(text, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        Stream<String> stream = Stream.of("A", "B", "C", "D");
        stream.forEach(System.out::println);
        // 1. 接口的实现类（传统匿名内部类，代码最繁琐）
        Function<String, Integer> f1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        // 2. Lambda表达式（最常用，只需关注参数和逻辑）
        Function<String, Integer> f2 = (s) -> s.length();

        // 3. 符合方法签名的静态方法引用
        Function<String, Integer> f3 = Demo::strLengthStatic;

        // 4. 符合方法签名的实例方法引用（特定对象的实例方法）
        Demo demo = new Demo();
        Function<String, Integer> f4 = demo::strLengthInstance;

        // 5. 符合方法签名的构造方法引用（String 的构造器接收 String，返回 String）
        Function<String, String> f5 = String::new;
    }
}

class NatualSupplier implements Supplier<Integer> {
    int n = 0;

    public Integer get() {
        n++;
        return n;
    }
}
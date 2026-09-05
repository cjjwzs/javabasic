package lambda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

public class CustomJoinWithPrefix {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 4, 5};

        Integer[] result88 = Arrays.stream(num)
                .map(x -> x * 2)
                .boxed()
                .toArray(Integer[]::new);

        String lines = "I Love Java 8 Stream!";

        // split by space, uppercase, and convert to Array
        String[] result77 =  Arrays.stream(lines.split("\\s+"))
                .map(String::toUpperCase)
                .toArray(value -> {
                    System.out.println("数组长度  "+value);
                    return new String[value];
                });
                //.toArray(String[]::new);

        Arrays.stream(lines.split("\\s+"))
                .map(String::toUpperCase).toArray(String[]::new);

        for (String s : result77) {
            System.out.println(s);
        }

        List<List<String>> classes = Arrays.asList(
                Arrays.asList("张三", "李四"),
                Arrays.asList("王五", "赵六")
        );

// ❌ 使用 map：结果是 Stream<List<String>>，依然带着两层壳子
        final Stream<Stream<String>> streamStream = classes.stream().map(list -> list.stream());
        streamStream.forEach((t) -> {
            t.forEach(System.out::println);
        });

// ✅ 使用 flatMap：结果是 Stream<String>，直接拿到所有名字
        classes.stream()
                .flatMap(Collection::stream) // 把内部的 List 拆成 Stream，再拍平
                .forEach(System.out::println);
// 输出：张三, 李四, 王五, 赵六
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Stream.of("a", "b").peek(System.out::println);
        // 因为没有终端操作，流未被激活，peek 不执行

        List<String> l = Arrays.asList("A", "B", "C", "D");

        List<String> result = l.stream()
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(result.size()); // 4


        List<String> numbers23 = Arrays.asList("1", "2", "A", "B", "C1D2E3");

        // 预编译正则表达式（复用提升性能）
        Pattern pattern = Pattern.compile("\\D+");

        List<List<String>> collect = numbers23.stream()
                .map(x -> {
                    Matcher matcher = pattern.matcher(x);
                    List<String> temp = new ArrayList<>();
                    // 循环查找所有匹配的非数字片段
                    while (matcher.find()) {
                        temp.add(matcher.group());
                    }
                    return temp;
                })
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
        Stream<String> language = Stream.of("java", "python", "node");

        //Convert a Stream to List
        List<String> result1 = language.collect(Collectors.toList());

        result1.forEach(System.out::println);

        List<Invoice> invoices = Arrays.asList(
                new Invoice("A01", BigDecimal.valueOf(9.99), BigDecimal.valueOf(1)),
                new Invoice("A02", BigDecimal.valueOf(19.99), BigDecimal.valueOf(1.5)),
                new Invoice("A03", BigDecimal.valueOf(4.99), BigDecimal.valueOf(2))
        );

        BigDecimal sumsum3 = invoices.stream()
                .map(x -> x.getQty().multiply(x.getPrice()))    // map
                .reduce(BigDecimal.ZERO, BigDecimal::add);      // reduce

        System.out.println(sumsum3);    // 49.955
        System.out.println(sumsum3.setScale(2, RoundingMode.HALF_UP));  // 49.96

        String[] strings = {"a", "b", "c", "d", "e"};

        // |a|b|c|d|e , the initial | join is not what we want
        String reduce = Arrays.stream(strings).reduce("", (a, b) -> a + "|" + b);

        // a|b|c|d|e, filter the initial "" empty string
        String reduce2 = Arrays.stream(strings).reduce("", (a, b) -> {
            if (!"".equals(a)) {
                return a + "|" + b;
            } else {
                return b;
            }
        });

        // a|b|c|d|e , better uses the Java 8 String.join :)
        String join = String.join("|", strings);


        int[] numbers1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int sum = Arrays.stream(numbers1).reduce(0, (a, b) -> a + b);    // 55
        int sum2 = Arrays.stream(numbers1).reduce(0, Integer::sum);      // 55

        int sum3 = Arrays.stream(numbers1).reduce(0, (a, b) -> a - b);   // -55
        int sum4 = Arrays.stream(numbers1).reduce(0, (a, b) -> a * b);   // 0, initial is 0, 0 * whatever = 0
        int sum5 = Arrays.stream(numbers1).reduce(11, (a, b) -> a / b);   // 0

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        sum = numbers.stream().mapToInt((x) -> x).sum();

        System.out.println(numbers.stream().collect(Collectors.averagingInt((x) -> x)));

// 输出：15
        System.out.println(sum);


        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int max = Arrays.stream(numbers2).reduce(0, (a, b) -> a > b ? a : b);  // 10
        int max1 = Arrays.stream(numbers2).reduce(0, Integer::max);            // 10

        int min = Arrays.stream(numbers2).reduce(0, (a, b) -> a < b ? a : b);  // 0
        int min1 = Arrays.stream(numbers2).reduce(0, Integer::min);            // 0


        List<String> names = Arrays.asList("张三", "李四", "王五");

        // 使用 Collector.of() 替代 CollectorImpl
        String result21 = names.stream()
                .collect(Collector.of(
                        StringBuilder::new, // 1. 提供初始容器
                        (sb, name) -> sb.append(name).append(", "), // 2. 累加元素
                        (sb1, sb2) -> sb1.append(sb2), // 3. 合并容器（并行流用）
                        sb -> { // 4. 最终转换：去掉末尾逗号和空格，并加上前缀
                            if (sb.length() == 0) return "员工列表：无";
                            return "员工列表：" + sb.substring(0, sb.length() - 2);
                        },
                        Collector.Characteristics.UNORDERED // 5. 特征集（可变参数）
                ));

        System.out.println(result21);
        // 输出：员工列表：张三, 李四, 王五

// 为了生成唯一的 key，我们需要一个外部计数器（注意：并行流下不安全，仅做串行演示）
        AtomicInteger index = new AtomicInteger(0);
        Map<Integer, String> result12 = names.stream()
                .collect(Collector.<String, Map<Integer, String>, Map<Integer, String>>of(
                        HashMap::new, // 1. 提供初始容器 HashMap
                        (map, name) -> map.put(index.getAndIncrement(), name), // 2. 累加：用递增索引作为Key，避免覆盖
                        (map1, map2) -> { // 3. 合并：将两个Map合并
                            map1.putAll(map2);
                            return map1;
                        },
                        map -> map, // 4. 【修复】finisher必须有返回值！这里直接返回Map本身
                        Collector.Characteristics.UNORDERED // 5. 特征集
                ));

        System.out.println(result12);
    }
}

class Invoice {

    String invoiceNo;
    BigDecimal price;
    BigDecimal qty;

    public Invoice() {
        super();
    }

    public Invoice(String invoiceNo, BigDecimal price, BigDecimal qty) {
        this.invoiceNo = invoiceNo;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceNo, invoice.invoiceNo) && Objects.equals(price, invoice.price) && Objects.equals(qty, invoice.qty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceNo, price, qty);
    }

    // getters, stters n constructor

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }
}
package stream;

import java.util.stream.Collector;
import java.util.stream.Stream;

public class CollectorsExample {
    public static void main(String[] args) {
        /**
         * public static<T, R> Collector<T, R, R> of(Supplier<R> supplier,
         *                                               BiConsumer<R, T> accumulator,
         *                                               BinaryOperator<R> combiner,
         *                                               Characteristics... characteristics)
         */
        Collector<String, StringBuilder, StringBuilder> stringCollector = Collector.of(
                StringBuilder::new,                        // 1. 创建容器
                (sb, str) -> sb.append(str).append(","),   // 2. 累加元素
                (sb1, sb2) -> sb1.append(sb2),             // 3. 合并容器（并行流用）
                Collector.Characteristics.UNORDERED        // 4. 声明特征：无序
        );

        Collector<Object, StringBuilder, StringBuilder> stringCollector1 = Collector.of(
                StringBuilder::new,
                (sb, str) -> sb.append(str).append("@"),
                (sb1, sb2) -> sb1.append(sb2),
                Collector.Characteristics.IDENTITY_FINISH
        );

// 使用自定义收集器
        String result = Stream.of("A", "B", "C")
                .collect(stringCollector)
                .toString();
        System.out.println(result); // 输出: A,B,C,

        String result1 = Stream.of("A", "B", "C")
                .collect(stringCollector1)
                .toString();
        System.out.println(result1); // 输出: A,B,C,
    }
}

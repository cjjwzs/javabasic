package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-27 15:55
 **/

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {

        //int[] -> IntStream -> Stream<Integer> -> Integer[]
        int[] num = {3, 4, 5, 6, 7, 8, 9, 10};
        final IntStream intStream1 = Arrays.stream(num);

        intStream1.forEach(System.out::println);

        //1. int[] -> IntStream
        IntStream stream = Arrays.stream(num);

        //2. IntStream -> Stream<Integer>
        Stream<Integer> boxed = stream.boxed();

        //3. Stream<Integer> -> Integer[]
        Integer[] result = boxed.toArray(Integer[]::new);

        System.out.println(Arrays.toString(result));

        // one line
        Integer[] oneLineResult = Arrays.stream(num).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(oneLineResult));
    }

}

package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-24 09:12
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestListMap {
    public TestListMap() {
    }

    public static void main(String[] args) {


        String[][] array = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        // Java 8
        String[] result = Stream.of(array)  // Stream<String[]>
                .flatMap(Stream::of)        // Stream<String>
                .toArray(String[]::new);    // [a, b, c, d, e, f]

        for (String s : result) {
            System.out.println(s);
        }


        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));
        //list.add(new Hosting(5, "mkyongK.com", 1));

        final Map<Integer, String> collectMap = list.stream().collect(Collectors.toMap(
                (key) -> key.getId(),
                (value) -> value.getName(),
                (o, o2) -> {
                    System.out.println("Key: " + o + " Value: " + o2);
                    return o;
                }
        ));
        System.out.println(collectMap);

        // key = id, value - websites
        Map<Integer, String> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getId, Hosting::getName));

        System.out.println("Result 1 : " + result1);

        // key = name, value - websites
        Map<String, Long> result2 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebsites));

        System.out.println("Result 2 : " + result2);

        // Same with result1, just different syntax
        // key = id, value = name
        Map<Integer, String> result3 = list.stream().collect(
                Collectors.toMap(x -> x.getId(), x -> x.getName()));

        System.out.println("Result 3 : " + result3);
    }
}

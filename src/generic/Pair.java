package generic;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-28 09:07
 **/

public class Pair<T> {
    private T first;
    private T last;

    public Pair(T first, T last) {
        this.first = first;
        this.last = last;
    }

    public T getFirst() {
        return first;
    }

    public T getLast() {
        return last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(last, pair.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    // 可以编译通过:
    public static <T> Pair<T> create(T first, T last) {
        return new Pair<T>(first, last);
    }

    T[] createArray(Class<T> cls) {
        return (T[]) Array.newInstance(cls, 5);
    }

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Map<String, Integer> map = new TreeMap<>();
        map.put("orange", 1);
        map.put("apple", 2);
        map.put("pear", 3);
        for (String key : map.keySet()) {
            System.out.println(key);
        }
        // apple, orange, pear

        Pair<Integer> p1 = Pair.create(1, 2);
        final Integer[] arrayInteger = p1.createArray(Integer.class);
        for ( int i = 0; i < arrayInteger.length; i++ ) {
            System.out.println(arrayInteger[i] = i);
        }
        System.out.println("First: " + p1.getFirst());
        System.out.println("Last: " + p1.getLast());

        Class<Integer> clazz = Integer.class;
        Constructor<Integer> cons = clazz.getConstructor(int.class);
        Integer i = cons.newInstance(123);

        System.out.println("i: " + i);

        Pair<String>[] ps = null; // ok
        Pair<String>[] ps2 = (Pair<String>[]) new Pair[2]; // compile error!

    }
}

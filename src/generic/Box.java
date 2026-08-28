package generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-28 10:42
 **/

public class Box<T> {


    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static <T extends Comparable<T>> int countGreaterThan(T[] anArray, T elem) {
        int count = 0;
        for (T e : anArray)
            if (e.compareTo(elem) > 0)  // compiler error
                ++count;
        return count;
    }

    public static <E> void append(List<E> list, Class<E> cls) throws Exception {
        E elem = cls.newInstance();   // OK
        list.add(elem);
    }

    public static void main(String[] args) {
        Map<String, List<String>> groupMap = new HashMap<>();

// 当键 "group1" 不存在时，才创建新的 ArrayList 并插入
// 随后直接向这个返回的列表中添加元素
        groupMap.computeIfAbsent("group1", k -> new ArrayList<>()).add("item1");
        Class<Pair> clazz = Pair.class;

        List<B> lb = new ArrayList<>();
        // List<A> la = lb;   // compile-time error
        Object someObject = new Object();
        Integer someInteger = new Integer(10);
        someObject = someInteger;   // OK

        Box<Integer> integerBox = new Box<Integer>();
        integerBox.set(new Integer(10));
        // integerBox.inspect("some text"); // error: this is still String!
    }
}

class A { /* ... */
};

interface B { /* ... */
};

interface C { /* ... */
};

class D<T extends A & B & C> { /* ... */
}
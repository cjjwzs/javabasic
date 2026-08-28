package generic;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DayDV;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.util.*;

import static java.util.List.*;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 16:35
 **/

public class Bag<T> {
    private T content;

    public Bag(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        content = clazz.newInstance();
    }

    public Bag() {
        super();
    }

    public Bag(T content) {
        this.content = content;
    }

    public T get() {
        return this.content;
    }

    public void set(T content) {
        this.content = content;
    }

    public boolean same(T t) {
        return this == t;
    }

    public static void printList(List<?> list) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        //Object obj = list.get(0);      // ✅ 可以读，返回 Object 类型
        //list.add("hello");          // ❌ 编译错误！不能添加任何具体元素
        list.add(null);                // ✅ 特例，可以添加 null

        // compile warning:
        Class clazz = String.class;
        String str = (String) clazz.newInstance();
        // no warning:
        Class<String> clazz1 = String.class;
        String str1 = clazz1.newInstance();

        Class<? super String> sup = String.class.getSuperclass();

        Class<Integer> clazz2 = Integer.class;
        Constructor<Integer> cons = clazz2.getConstructor(int.class);
        Integer i = cons.newInstance(123);
        System.out.println("i: " + i);

        //我们可以声明带泛型的数组，但不能用new 操作符创建带泛型的数组：
        Bag<String>[] ps = null; // ok
        //Bag<String>[] ps1 = new Bag<String>[2]; // compile error!
        Bag<String>[] ps22 = (Bag<String>[]) new Bag[2];
        char data[] = {'a', 'b', 'c'};
        String str2 = new String(data);
        System.out.println("str2: " + str2);


    }

    static class ArrayHelper {
        @SafeVarargs
        static <T> T[] asArray(T... objs) {
            return objs;
        }
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<DayOfWeek,String> map3 = new EnumMap<>(DayOfWeek.class);
        map3.put(DayOfWeek.MONDAY, "Monday");
        map3.forEach((dayOfWeek, s) -> {
            System.out.println(s+" "+dayOfWeek);
        });
        Map<DayOfWeek, String> map1 = new EnumMap<>(DayOfWeek.class);
        map1.put(DayOfWeek.MONDAY, "星期一");
        map1.put(DayOfWeek.TUESDAY, "星期二");
        map1.put(DayOfWeek.WEDNESDAY, "星期三");
        map1.put(DayOfWeek.THURSDAY, "星期四");
        map1.put(DayOfWeek.FRIDAY, "星期五");
        map1.put(DayOfWeek.SATURDAY, "星期六");
        map1.put(DayOfWeek.SUNDAY, "星期日");
        System.out.println(map1);
        System.out.println(map1.get(DayOfWeek.MONDAY));

        String key1 = "a";
        Map<String, Integer> map = new HashMap<>();
        map.put(key1, 123);
        String key2 = new String("a");
        map.get(key2); // 123
        System.out.println(key1 == key2); // false
        System.out.println(key1.equals(key2)); // true

        printList(new ArrayList<String>());
        String[] ss = ArrayHelper.asArray("a", "b", "c");
        Integer[] ns = ArrayHelper.asArray(1, 2, 3);

        List<String> list1 = Arrays.asList("A", "B", "C");
        System.out.println(list1.contains(new String("C"))); // true or false?
        System.out.println(list1.indexOf(new String("C"))); // 2 or -1?
        Objects.hash("567");
        Bag<String> bag = new Bag<String>("mybook");
        //Integer content1 = bag.get(); //编译出错
        String content2 = bag.get(); //合法，无须进行强制类型转换
        System.out.println(content2);

        // 可以省略后面的Number，编译器可以自动推断泛型类型：
        List<Number> list = new ArrayList<>();
        list.add(new Integer(1));
        list.add(new Float(2));
        for (Number number : list) {
            System.out.println(number);
        }

        Bag<String> p1 = new Bag<>("Hello");
        Bag<Integer> p2 = new Bag<>(1236);
        Class c1 = p1.getClass();
        Class c2 = p2.getClass();
        System.out.println(c1 == c2); // true
        System.out.println(c1 == Bag.class); // true

        Bag<ArrayList> bg = new Bag<ArrayList>(ArrayList.class);
        bg.set(new ArrayList<>());
        bg.get().add(123);
        System.out.println(bg.get());

        Class<IntPair> clazz = IntPair.class;
        Type t = clazz.getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            Type[] types = pt.getActualTypeArguments(); // 可能有多个泛型类型
            Type firstType = types[0]; // 取第一个泛型类型
            Class<?> typeClass = (Class<?>) firstType;
            System.out.println(typeClass); // Integer
        }

    }
}

class IntPair extends Bag<Integer> {
}
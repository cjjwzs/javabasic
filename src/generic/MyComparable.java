package generic;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-28 11:56
 **/


interface Comparable01<T> {
    T compareTo(T other);
}
public class MyComparable implements Comparable01<MyComparable> {
    @Override
    public MyComparable compareTo(MyComparable other) {
        return new MyComparable();
    }
}
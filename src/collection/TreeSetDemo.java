package collection;

import java.util.*;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-20 17:23
 **/

public class TreeSetDemo {
    public static void main(String[] args) {

        List<Integer> list=new ArrayList<Integer>();
        list.add(new Integer(3));
        list.add(new Integer(4));
        list.add(new Integer(3));
        list.add(new Integer(2));
        //Collections.sort(list); //为列表中的元素进行排序
        Collections.sort(list, (o1, o2) -> o2.compareTo(o1));
        for(Integer i:list)
            System.out.print(i+" ");
        System.out.println("");
        System.out.println("==============");
        Set<Integer> set = new TreeSet<Integer>();
        set.add(8); //自动装箱，把 8转换为相应的 Integer对象，再加入到 Set中
        set.add(7);
        set.add(6);
        set.add(10);
        set.add(9);
        for (int i : set) //自动拆箱，把集合中的 Integer对象转换为 int基本类 型的数据
            System.out.print(i + " ");

    }
}
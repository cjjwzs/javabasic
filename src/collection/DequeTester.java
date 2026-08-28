package collection;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 10:43
 **/

public class DequeTester {
    public static void main(String args[]) {
        Deque<String> queue = new ArrayDeque<String>();
        queue.add("老二"); //向队列末尾添加元素
        queue.addFirst("老大"); //向队列头部添加元素
        queue.addLast("老三"); //向队列末尾添加元素
        queue.add("老四"); //向队列末尾添加元素
        System.out.print("遍历双向队列：");
        for (String e : queue)
            System.out.print(e + " ");
        System.out.println("\n 删 除 双 向 队 列 的 最 后 一 个 元素："+queue.removeLast());
    }
}
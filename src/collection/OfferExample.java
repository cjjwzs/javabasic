package collection;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 10:53
 **/

import java.util.ArrayDeque;
import java.util.Deque;

public class OfferExample {
    public static void main(String[] args) {
        Deque<String> queue = new ArrayDeque<>();

        // 调用 offer 将元素插入队尾
        boolean isSuccess = queue.offer("任务A");

        if (isSuccess) {
            System.out.println("任务A成功加入队列！"); // 输出此行
        } else {
            System.out.println("队列已满，任务A加入失败。");
        }

        System.out.println("当前队列：" + queue); // 输出：当前队列：[任务A]
    }
}
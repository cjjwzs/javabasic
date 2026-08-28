package collection;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 10:57
 **/

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author caojj08267
 * @date 2026/05/21
 */
public class CapacityLimitExample {
    public static void main(String[] args) throws InterruptedException {
        // 1. 初始化一个有界双端队列，强制限制最大容量为 3
        LinkedBlockingDeque<String> boundedDeque = new LinkedBlockingDeque<>(3);

        // 2. 连续尝试插入 4 个元素
        System.out.println("插入第1个元素: " + boundedDeque.offer("A")); // 队列: [A]
        System.out.println("插入第2个元素: " + boundedDeque.offer("B")); // 队列: [A, B]
        System.out.println("插入第3个元素: " + boundedDeque.offer("C")); // 队列: [A, B, C] (此时已满)

        // 3. 插入第4个元素，触发“容量限制”
        final boolean isSuccess = boundedDeque.offer("D");

        String firstElement = boundedDeque.element();

        System.out.println("firstElement: " + firstElement);

        final String first = boundedDeque.getLast();
        System.out.println("first: " + first);

        // 4. 打印结果
        System.out.println("插入第4个元素: " + isSuccess); // 输出: false
        System.out.println("当前队列内容: " + boundedDeque); // 输出: [A, B, C]
    }
}
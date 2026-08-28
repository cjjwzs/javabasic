package collection;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 11:15
 **/

import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeDemo {
    public static void main(String[] args) {
        // 1. 创建一个最大容量为 3 的有界阻塞双端队列
        LinkedBlockingDeque<String> deque = new LinkedBlockingDeque<>(3);

        // 2. 启动生产者线程
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    String item = "商品-" + i;
                    // 使用 putLast 在队列尾部插入。如果队列满了(达到3个)，这里会自动阻塞等待
                    deque.putLast(item);
                    System.out.println("🟢 生产者生产了: " + item + "，当前队列数量: " + deque.size());
                    Thread.sleep(500); // 模拟生产耗时
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-Thread").start();

        // 3. 启动消费者线程
        new Thread(() -> {
            try {
                // 模拟消费者一直运行，直到生产者结束且队列为空（此处简化为消费5次）
                for (int i = 1; i <= 5; i++) {
                    // 使用 takeFirst 从队列头部取出。如果队列空了，这里会自动阻塞等待
                    String item = deque.takeFirst();
                    System.out.println("🔴 消费者消费了: " + item + "，当前队列数量: " + deque.size());
                    Thread.sleep(1500); // 模拟消费耗时（故意比生产慢，触发阻塞效果）
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-Thread").start();
    }
}
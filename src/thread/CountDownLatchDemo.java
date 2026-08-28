package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 09:41
 **/

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 初始化一个计数器为 3 的门闩
        CountDownLatch latch = new CountDownLatch(3);

        // 模拟三个子模块的初始化线程
        new Thread(() -> {
            System.out.println("正在初始化【数据库连接】...");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("【数据库连接】初始化完成！");
            latch.countDown(); // 计数器减 1
        }).start();

        new Thread(() -> {
            System.out.println("正在初始化【缓存服务】...");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("【缓存服务】初始化完成！");
            latch.countDown(); // 计数器减 1
        }).start();

        new Thread(() -> {
            System.out.println("正在初始化【网络模块】...");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("【网络模块】初始化完成！");
            latch.countDown(); // 计数器减 1
        }).start();

        // 主线程调用 await() 阻塞等待，直到上面三个线程都执行了 countDown()
        System.out.println("主线程正在等待所有服务初始化完成...");
        latch.await();
        // 只有当计数器变为 0 时，主线程才会走到这里
        System.out.println("所有服务初始化完毕，主程序正式启动！");
    }
}
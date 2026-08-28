package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 07:50
 **/

import java.util.concurrent.Semaphore;

public class DatabaseConnectionPool {
    // 假设只有 3 个可用的数据库连接（许可证）
    private static final int MAX_CONNECTIONS = 3;
    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS);

    public void accessDatabase(int threadId) {
        try {
            // 1. 尝试获取许可证（获取数据库连接）
            // 如果没有可用连接，线程会在这里阻塞等待
            semaphore.acquire();

            System.out.println("线程 " + threadId + " 获取到了连接，开始执行数据库操作...");
            // 模拟数据库操作耗时
            Thread.sleep(2000);
            System.out.println("线程 " + threadId + " 操作完成。");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 2. 务必在 finally 块中释放许可证（归还连接）
            // 保证即使发生异常，连接也能被归还，防止资源耗尽
            semaphore.release();
            System.out.println("线程 " + threadId + " 释放了连接。");
        }
    }

    public static void main(String[] args) {
        DatabaseConnectionPool pool = new DatabaseConnectionPool();
        // 模拟 10 个线程同时发起请求
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> pool.accessDatabase(finalI), "线程-" + i).start();
        }
    }
}
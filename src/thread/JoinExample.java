package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-17 10:37
 **/

public class JoinExample {

    public static void main(String[] args) {
        System.out.println("Main thread started.");

        Thread workerThread = new Thread(() -> {
            try {
                System.out.println("Worker thread started.");
                // 模拟一个耗时的操作
                Thread.sleep(3000);
                System.out.println("Worker thread finished its task.");
            } catch (InterruptedException e) {
                // 如果线程在睡眠期间被中断，则处理中断
                System.out.println("Worker thread was interrupted.");
                Thread.currentThread().interrupt(); // 重置中断状态
            }
        });

        workerThread.start(); // 启动子线程

        System.out.println("Main thread is doing other work immediately after starting the worker...");

        try {
            // Main 线程在这里调用 join()，开始等待 workerThread 执行完毕
            System.out.println("Main thread is waiting for worker thread to finish...");
            workerThread.join(); // Main 线程被阻塞在这里，直到 workerThread 结束
            System.out.println("Worker thread has finished. Main thread can now proceed with dependent logic.");
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
            Thread.currentThread().interrupt(); // 重置中断状态
        }

        System.out.println("Main thread finished.");
    }
}
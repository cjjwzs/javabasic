package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 07:32
 **/

public class StaticLockExample {

    // 静态同步方法，锁的是 StaticLockExample.class
    public static synchronized void staticMethod() {
        System.out.println(Thread.currentThread().getName() + " 进入了静态同步方法");
        try {
            Thread.sleep(2000); // 模拟耗时操作，霸占 Class 锁 2 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 离开了静态同步方法");
    }

    public static void main(String[] args) {
        // 线程 1 调用
        new Thread(StaticLockExample::staticMethod, "线程A").start();

        // 稍微延迟，确保线程A先启动
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }

        // 线程 2 调用（即使是不同的调用方式，锁依然是同一个）
        new Thread(StaticLockExample::staticMethod, "线程B").start();
    }
}
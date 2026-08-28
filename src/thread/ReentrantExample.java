package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-20 10:26
 **/

public class ReentrantExample {

    private final Object lock = new Object();

    public void method1() {
        synchronized (lock) { // 线程首次获取lock
            System.out.println("Acquired lock in method1");
            method2(); // 尝试调用method2
        }
    }

    public void method2() {
        synchronized (lock) { // 线程再次尝试获取同一个lock
            System.out.println("Acquired same lock again in method2");
            // do something
        }
        // 退出method2的synchronized块，对lock的计数减一
    }

    public static void main(String[] args) {
        ReentrantExample example = new ReentrantExample();
        Thread t = new Thread(example::method1);
        t.start();
    }
}
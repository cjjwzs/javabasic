package thread;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-20 10:42
 **/

public class Counter {
    private int count = 0;

    public void add(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public void dec(int n) {
        synchronized (this) {
            count -= n;
        }
    }

    public int get() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        // 对c1进行操作的线程:
        new Thread(() -> {
            c1.add(1);
        }).start();
        new Thread(() -> {
            c1.dec(2);
        }).start();
        // 对c2进行操作的线程:
        new Thread(() -> {
            c2.add(1);
        }).start();
        new Thread(() -> {
            c2.dec(2);
        }).start();
        //Thread.sleep(4000);
        System.out.println("c1: " + c1.get());
        System.out.println("c2: " + c2.get());

    }
}
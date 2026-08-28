package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 11:29
 **/

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC {
    private final Lock lock = new ReentrantLock();
    // 创建三个条件队列，分别代表轮到打印A、B、C的状态
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    private int state = 1; // 1代表该打A，2代表该打B，3代表该打C

    // 打印A的方法
    public void printA() {
        print("A", 1, conditionA, conditionB);
    }

    // 打印B的方法
    public void printB() {
        print("B", 2, conditionB, conditionC);
    }

    // 打印C的方法
    public void printC() {
        print("C", 3, conditionC, conditionA);
    }

    // 核心打印逻辑
    private void print(String name, int targetState, Condition currentCond, Condition nextCond) {
        lock.lock();
        try {
            // 如果轮次不对，就在自己的专属条件队列里等着
            while (state != targetState) {
                currentCond.await();
            }
            System.out.print(name);
            // 打印完更新状态，并精准唤醒下一个该打印的线程
            state = (state % 3) + 1;
            nextCond.signal();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    // ================= 测试入口 =================
    public static void main(String[] args) {
        PrintABC printABC = new PrintABC();

        // 启动线程1：负责打印A
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printABC.printA();
            }
        }, "Thread-A").start();

        // 启动线程2：负责打印B
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printABC.printB();
            }
        }, "Thread-B").start();

        // 启动线程3：负责打印C
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printABC.printC();
            }
        }, "Thread-C").start();
    }
}
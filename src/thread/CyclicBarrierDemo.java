package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 09:10
 **/

import java.util.Stack;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 创建一个需要 3 个线程等待的屏障，并设置屏障动作
        CyclicBarrier barrier = new CyclicBarrier(4, () -> {
            // 这个任务由最后一个到达屏障的线程执行
            System.out.println("【屏障动作】所有线程已就位，开始执行数据汇总！");
        });

        for (int i = 0; i < 4; i++) {
            final int threadNum = i;
            new Thread(() -> {
                try {
                    System.out.println("线程 " + threadNum + " 正在准备第一阶段数据...");
                    Thread.sleep((threadNum + 1) * 1000); // 模拟不同耗时的准备工作
                    System.out.println("线程 " + threadNum + " 准备就绪，等待其他线程...");

                    // 调用 await() 表示当前线程已到达屏障点，开始阻塞等待
                    barrier.await();


                    System.out.println("线程 " + threadNum + " 屏障已打开，开始执行第二阶段任务！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
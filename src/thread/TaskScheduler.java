package thread;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-21 12:03
 **/

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Task1 {
    String name;
    int priority; // 优先级，数值越大越紧急

    public Task1(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}

public class TaskScheduler {
    public static void main(String[] args) {

        Integer[] array={11,22,33};
        List<Integer> list= Arrays.asList(array); // 把 数 组 转 换 为 List

        // 创建大根堆：通过比较器实现 priority 降序排列（b - a）
        PriorityQueue<Task1> taskQueue = new PriorityQueue<>(
                (t1, t2) -> Integer.compare(t2.priority, t1.priority)
        );

        taskQueue.offer(new Task1("普通任务", 1));
        taskQueue.offer(new Task1("紧急任务", 10));
        taskQueue.offer(new Task1("中等任务", 5));

        taskQueue.add(new Task1("测试任务",2222));

        // 取出任务，会严格按照优先级 10 -> 5 -> 1 的顺序
        while (!taskQueue.isEmpty()) {
            Task1 task = taskQueue.poll();
            System.out.println("正在执行：" + task.name + "，优先级：" + task.priority);
        }
    }
}
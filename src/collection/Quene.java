package collection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-20 18:15
 **/

public class Quene {
    public static void main(String[] args) throws IOException {
        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        System.out.println(queue.poll()); // 输出: first


        Queue<String> queue1 = new ArrayBlockingQueue<>(2); // 容量为 2

        queue1.add("Element 1"); // 成功
        queue1.add("Element 2"); // 成功

        //queue.add("Element 3"); // 尝试添加第三个元素，会抛出 IllegalStateException!

        System.out.println(queue1.remove("Element 3")); // offer() 返回 false，而不是抛异常

        Queue<String> q = new PriorityQueue<>();
// 添加3个元素到队列:
        q.offer("apple");
        q.offer("pear");
        q.offer("banana");
        System.out.println(q.poll()); // apple
        System.out.println(q.poll()); // banana
        System.out.println(q.poll()); // pear
        System.out.println(q.poll()); // null,因为队列为空
        System.out.println(q.peek());

        readFile();

    }

    public static void readFile() throws IOException {
        try (InputStream input = new FileInputStream("src/readme.txt")) {
            int n;
            while ((n = input.read()) != -1) {
                System.out.println(n);
            }
        } // 编译器在此自动为我们写入finally并调用close()
    }
}
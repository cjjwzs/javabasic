package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-26 17:24
 **/

import java.util.concurrent.CompletableFuture;

public class ThenApplyDemo {
    public static void main(String[] args) {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(s -> s + " test");
        System.out.println("future = " + future.join());

        CompletableFuture<User> userFuture = CompletableFuture
                .supplyAsync(() -> {
                    // 1. 第一步：异步获取原始的 JSON 字符串
                    System.out.println("1. 异步获取JSON，当前线程：" + Thread.currentThread().getName());
                    return "{\"id\":1001, \"name\":\"张三\"}";
                })
                .thenApply(json -> {
                    // 2. 第二步：同步将 JSON 字符串解析（转换）为 User 对象
                    System.out.println("2. 同步解析JSON，当前线程：" + Thread.currentThread().getName());
                    // 模拟解析过程
                    return new User(1001, "张三");
                })
                .thenApply(user -> {
                    // 3. 第三步：继续同步加工，给用户名加上前缀
                    System.out.println("3. 同步加工用户名，当前线程：" + Thread.currentThread().getName());
                    user.setName("尊贵的用户：" + user.getName());
                    return user;
                });

        System.out.println("最终结果：" + userFuture.join());
    }

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }
}
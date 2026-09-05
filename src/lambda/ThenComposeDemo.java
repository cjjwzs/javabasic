package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-26 17:49
 **/

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class ThenComposeDemo {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> "hello") // 异步返回 "hello"
                .thenApply(s -> s.toUpperCase())         // 同步转换为 "HELLO"
                .thenAccept(System.out::println);        // 消费结果，输出: HELLO

        CompletableFuture<String> finalResult =
                // 第一步：异步获取用户ID
                CompletableFuture.supplyAsync(() -> {
                            System.out.println("1. 获取用户ID，当前线程：" + Thread.currentThread().getName());
                            return "1001";
                        })
                        // 第二步：用ID异步查询用户信息（注意这里返回的是 CompletableFuture）
                        .thenCompose(userId -> {
                            System.out.println("2. 准备查询用户信息，当前线程：" + Thread.currentThread().getName());
                            return getUserInfoAsync(userId);
                        })
                        // 第三步：用用户信息异步查询部门名称
                        .thenCompose(user -> {
                            System.out.println("3. 准备查询部门信息，当前线程：" + Thread.currentThread().getName());
                            return getDepartmentAsync(user.deptId);
                        });

        System.out.println("最终部门名称：" + finalResult.join());
        findUser();
    }

    public static void findUser() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.printf("1. findUser 当前线程 ：" + Thread.currentThread().getName());
            return "1001";
        }).thenCompose(userName -> {
            return CompletableFuture.supplyAsync(
                    ()->{
                        return userName;
                    }
            );
        });
        stringCompletableFuture.join();
    }

    // 模拟异步查询用户信息
    private static CompletableFuture<User> getUserInfoAsync(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("   -> 正在异步查询用户 " + userId + " 的信息");
            return new User(userId, "张三", "D01");
        });
    }

    // 模拟异步查询部门信息
    private static CompletableFuture<String> getDepartmentAsync(String deptId) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("   -> 正在异步查询部门 " + deptId + " 的名称");
            return "技术部";
        });
    }

    static class User {
        String id;
        String name;
        String deptId;

        public User(String id, String name, String deptId) {
            this.id = id;
            this.name = name;
            this.deptId = deptId;
        }
    }
}
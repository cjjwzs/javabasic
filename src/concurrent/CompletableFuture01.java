package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFuture01 {
    {
        int x = 12;
        {
            int y = 96; // Illegal
        }
    }

    public static void thenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = future1.thenCompose(s ->
                CompletableFuture.supplyAsync(() -> s + " World")
        );



        System.out.println(future2.get()); // 输出: Hello World
    }

    public static void theApply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> thenApplyStr = future1.thenApply(s -> s + " World");
        System.out.println(thenApplyStr.get());
    }

    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<Void> voidCompletableFuture = future1.thenAccept(s -> {
            System.out.println(s);
        });
        System.out.printf("thenAccept返回结果999999   %s", voidCompletableFuture.get());
        System.out.println();
    }

    public static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "combine1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "combine2");
        CompletableFuture<String> stringCompletableFuture = future1.thenCombine(future2, (s1, s2) -> s1 + s2);
        System.out.println(stringCompletableFuture.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenCompose();
        System.out.println("thenApply begin");
        theApply();
        System.out.println("thenAccept begin");
        thenAccept();
        System.out.println("thenCombine begin");
        thenCombine();

        {
            int x = 12;
            {
                int y = 96; // Illegal
            }
            //System.out.println(y); Cannot resolve symbol 'y' 作用域之外的变量
            System.out.println(x);
        }

    }
}

package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-26 07:49
 **/

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingByExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> welcomeFuture = CompletableFuture
                .supplyAsync(() -> {
                    // 1. 第一步：异步查询，返回一个 User 对象
                    System.out.println("1. 查询用户，当前线程：" + Thread.currentThread().getName());
                    return new Employee("张三", "技术部", LocalDate.of(2023, 5, 1));
                })
                .thenApplyAsync(employee -> {
                    System.out.println("2. 提取用户名，当前线程：" + Thread.currentThread().getName());
                    return employee.getHireDate();
                })
//                .thenApply(user -> {
//                    // 2. 第二步：接收 User 对象，提取并返回用户名 (String)
//                    System.out.println("2. 提取用户名，当前线程：" + Thread.currentThread().getName());
//                    return user.getName();
//                })
                .thenApplyAsync(name -> {
                    // 3. 第三步：接收用户名，加工并返回最终的欢迎语 (String)
                    System.out.println("3. 生成欢迎语，当前线程：" + Thread.currentThread().getName());
                    return "欢迎你，" + name + "！";
                });

        // 获取最终结果
        System.out.println("最终结果：" + welcomeFuture.join());

        // 场景：并行查询“商品库存”和“用户限购数量”，最后计算实际可购买数量
        CompletableFuture<Integer> stockFuture = CompletableFuture.supplyAsync(() -> {
            return 100; // 模拟查库存耗时
        });
        CompletableFuture<Integer> limitFuture = CompletableFuture.supplyAsync(() -> {
            return 5;   // 模拟查限购耗时
        });

// 两个任务都完成后，合并结果
        CompletableFuture<Integer> buyableFuture = stockFuture.thenCombine(limitFuture, (stock, limit) -> {
            return Math.min(stock, limit); // 可购买数量 = 取两者最小值
        });

        System.out.println("用户最终可购买数量为：" + buyableFuture.join()); // 输出：5\

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                    if (true) throw new RuntimeException("查询服务异常！");
                    return "正常数据";
                })
                .exceptionally(ex -> {
                    System.err.println("发生异常，返回默认兜底数据。原因：" + ex.getMessage());
                    return "【默认兜底数据】"; // 异常时返回的备用结果
                });

        System.out.println("最终结果：" + future.get()); // 输出：最终结果：【默认兜底数据】

        // 准备数据：故意打乱部门和顺序

        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", LocalDate.of(2023, 5, 1)),
                new Employee("李四", "市场部", LocalDate.of(2023, 3, 1)),
                new Employee("王五", "技术部", LocalDate.of(2023, 8, 1)),
                new Employee("赵六", "人事部", LocalDate.of(2023, 3, 15)),
                new Employee("孙七", "市场部", LocalDate.of(2023, 11, 1))
        );

        //Map<String, Map<String, List<WpaManagerEvaluateDTO>>> data

        Map<String, Map<String, List<Employee>>> hashMap = new HashMap();
        hashMap.put("key1", new HashMap<String, List<Employee>>() {{
            this.put("name", employees);
        }});
        hashMap.put("key2", new HashMap<String, List<Employee>>() {{
            this.put("name2", employees);
        }});

        final Stream<Employee> employeeStream = hashMap.values()
                .stream()
                .map(Map::values)
                .flatMap(Collection::stream)
                //.flatMap(List::stream);
                .flatMap(Collection::stream);

        final CompletableFuture<Stream<String>> streamCompletableFuture = CompletableFuture
                .supplyAsync(() -> employees.stream()
                        .map(Employee::getDepartment));
        final List<String> collectList = streamCompletableFuture.join().collect(Collectors.toList());
        collectList.forEach(System.out::println);

        final Map<String, Long> collectMap = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.mapping(ele -> ele.getHireDate(), Collectors.counting())
        ));
        //collectMap.forEach((k, v) -> System.out.println(k + " -> " + v + "人"));

        // ==========================================
        // 1. 单参数版：简单粗暴，只分组，不统计
        // 默认返回 Map<String, List<Employee>>
        // ==========================================
        Map<String, List<Employee>> simpleGroup = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("【单参数版结果】(默认HashMap，顺序随机):");
        simpleGroup.forEach((k, v) -> System.out.println(k + " -> " + v.size() + "人"));


        // ==========================================
        // 2. 双参数版：分组 + 统计 (最常用)
        // 统计每个部门的人数，默认返回 Map<String, Long>
        // ==========================================
        Map<String, Long> countGroup = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting() // 下游收集器：统计数量
                ));

        System.out.println("\n【双参数版结果】(统计人数，默认HashMap，顺序随机):");
        countGroup.forEach((k, v) -> System.out.println(k + " -> " + v + "人"));


        // ==========================================
        // 3. 三参数版：分组 + 指定Map类型 + 统计 (终极版)
        // 使用 TreeMap::new，让结果按部门名称自动排序
        // ==========================================
        Map<String, Long> sortedCountGroup = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        TreeMap::new,         // 指定外层容器为 TreeMap (按Key排序)
                        Collectors.counting() // 下游收集器：统计数量
                ));

        System.out.println("\n【三参数版结果】(使用TreeMap，按部门名称 A-Z 排序):");
        sortedCountGroup.forEach((k, v) -> System.out.println(k + " -> " + v + "人"));
    }

    // 简单的员工类
    static class Employee {
        String name;
        String department;
        LocalDate hireDate;

        public Employee(String name, String department, LocalDate hireDate) {
            this.name = name;
            this.department = department;
            this.hireDate = hireDate;
        }

        public String getDepartment() {
            return department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDate getHireDate() {
            return hireDate;
        }

        public void setHireDate(LocalDate hireDate) {
            this.hireDate = hireDate;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", department='" + department + '\'' +
                    ", hireDate=" + hireDate +
                    '}';
        }
    }
}
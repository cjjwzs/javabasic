package lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-25 08:09
 **/

public class GroupingByStats {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", 15000, 25),
                new Employee("李四", "技术部", 18000, 30),
                new Employee("王五", "市场部", 12000, 28),
                new Employee("赵六", "市场部", 13000, 32),
                new Employee("孙七", "人事部", 10000, 35),
                new Employee("周八", "人事部", 11000, 29)
        );


        // 1. 统计每个部门的人数
        Map<String, Long> countByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("各部门人数：" + countByDept);

        // 2. 计算每个部门的平均工资
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("各部门平均工资：" + avgSalaryByDept);

        // 3. 计算每个部门的工资总和
        Map<String, Double> sumSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println("各部门工资总额：" + sumSalaryByDept);

        // 4. 获取每个部门的最高工资员工
        Map<String, Optional<Employee>> maxSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));
        System.out.println("各部门最高工资员工：" + maxSalaryByDept);

        // 5. 获取每个部门的工资统计信息
        Map<String, DoubleSummaryStatistics> statsByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)
                ));
        System.out.println("各部门工资统计：");
        statsByDept.forEach((dept, stats) -> {
            System.out.printf("%s - 最高:%.0f, 最低:%.0f, 平均:%.1f, 总和:%.0f%n",
                    dept, stats.getMax(), stats.getMin(),
                    stats.getAverage(), stats.getSum());
        });
    }
}
package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-25 08:17
 **/

public class MultiLevelGrouping {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", 15000, 25),
                new Employee("李四", "技术部", 18000, 30),
                new Employee("王五", "市场部", 12000, 28),
                new Employee("赵六", "市场部", 13000, 32),
                new Employee("孙七", "人事部", 10000, 35),
                new Employee("周八", "人事部", 11000, 29)
        );

        final Map<String, Map<String, List<Employee>>> multiGroups = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.groupingBy(e -> e.getAge() < 30 ? "青年" : "中青年")
                ));

        // 按部门分组，然后再按年龄分组
        Map<String, Map<String, List<Employee>>> multiGroup = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.groupingBy(e -> e.getAge() < 30 ? "青年" : "中青年")
                ));

        System.out.println("多级分组结果：");
        multiGroup.forEach((dept, ageGroup) -> {
            System.out.println(dept + ":");
            ageGroup.forEach((age, emps) -> {
                System.out.println("  " + age + ": " + emps);
            });
        });
    }
}
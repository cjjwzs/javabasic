package lambda;

/**
 * @author: caojj08267
 * @Desc:
 * @create: 2026-05-25 07:57
 **/

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;

    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s(%.0f)", name, salary);
    }
}

class GroupingByDemo {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("张三", "技术部", 15000, 25),
                new Employee("李四", "技术部", 18000, 30),
                new Employee("王五", "市场部", 12000, 28),
                new Employee("赵六", "市场部", 13000, 32),
                new Employee("孙七", "人事部", 10000, 35),
                new Employee("周八", "人事部", 11000, 29)
        );

        // 按部门分组
        Map<String, List<Employee>> byDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("按部门分组：");
        byDept.forEach((dept, emps) -> {
            System.out.println(dept + ": " + emps);
        });
    }
}
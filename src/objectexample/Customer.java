package objectexample;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Cloneable {


    private String name;
    private int age;

    public Customer() {
        this("unkonw",0);
        System.out.println("call default constructor");
    }

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("call second constructor");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "toString [" + name + ", " + age + "]";
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, CloneNotSupportedException {
        Class objClass = Class.forName("objectexample.Customer");
        Customer customer = (Customer) objClass.newInstance();
        System.out.println(customer);

        Customer customer1 = (Customer)customer.clone(); //不会调用Customer类的构造方法
        System.out.println(customer1);

        System.out.printf("对比 "+customer1.equals(customer));
    }
}

class SubCustomer extends Customer {
    public SubCustomer() {
        super();
    }
}

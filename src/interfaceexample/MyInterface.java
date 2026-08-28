package interfaceexample;

public interface MyInterface {
    String myName = "liming";
    Integer age = 20;

    default void print(){
        System.out.println(this.myName+"  "+this.age);
    }

    static void staticPrint(){
        System.out.println("staticPrint");
    }

    void myMethod();
    public static void main(String[] args) {
        new MyInterface() {
            @Override
            public void print() {
                MyInterface.super.print();
            }

            @Override
            public void myMethod() {

            }
        }.print();
    }
}

class MyInerfaceImpl implements MyInterface {

    @Override
    public void myMethod() {
        System.out.println("myMethod");
    }

    public static void main(String[] args) {
        MyInterface myInterface = new MyInerfaceImpl();
        myInterface.myMethod();
        //myInterface.staticPrint();//Static method may be invoked on containing interface class only
        myInterface.print();
    }
}
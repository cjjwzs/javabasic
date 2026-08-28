package interfaceexample;

public interface MyInterface {
    String myName = "liming";
    Integer age = 20;

    default void print(){
        System.out.println(this.myName+"  "+this.age);
    }

    public static void main(String[] args) {
        new MyInterface() {
            @Override
            public void print() {
                MyInterface.super.print();
            }
        }.print();
    }
}

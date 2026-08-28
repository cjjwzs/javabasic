package init;

import java.lang.ref.PhantomReference;

class Tester{
    //public static final int a=2*3; //变量 a是编译时常量
    public static final int a=(int)(Math.random()*5); //变
    //量 a不是编译时常量
    static{
        System.out.println("init Tester");
    }
}
class Sample{
    public static void main(String args[]){
        System.out.println("a="+ Tester.a ); //打印 a=6

        String s1="Hello";
        String s2="Hello"; //s2和 s1引用同一个 String对象
        String s3=new String("Hello");
        System.out.println(s1==s2); //打印 true
        System.out.println(s1==s3); //打印 false

        s1="H";
        s2="ello";
        s3=s1 +s2 ; //s3引用一个新的 String对象
        System.out.println(s3=="Hello"); //打印 false
        System.out.println(s3.equals("Hello")); //打印 true
    }
}
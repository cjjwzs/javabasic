public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        int i=(int)11.2; //合法
        float f=13L; //合法
        float ff =0x0022; //合法
        float f1=11; //编译出错
        System.out.println(f1);
        float f2=(float)11.2; //合法
        char c='老';
        System.out.println(c);
        int a = 0;
        System.out.println("a="+a);
        System.out.println(++a);

        String str1 = "ab";
        String str2 = "ab";
        System.out.println(str1 == str2);

        String s = "abc";
        String ss = "ab" + "c";
        System.out.println(s == ss);

        byte b1 = 12;
        byte b2 = 32;
        final int i1 = b1 + b2;
        float ff1 = 23.126f ;
        System.out.println(ff1);
        System.out.println(8&3);

    }
}
class Counter{
    public int count1=0;
    public static int count2=0;
}
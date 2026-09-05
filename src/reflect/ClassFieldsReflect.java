package reflect;

import java.lang.reflect.Field;

/**
 * `java.lang.Class` 反射 API
 * <p>
 * >
 * > **返回本类以及所有父类中所有 `public` 的字段（成员变量），包含继承来的 public 字段；
 * 不包含 private/protected/default。**
 * <p>
 * ## 和 `getDeclaredFields()`
 */
public class ClassFieldsReflect {
    public static void main(String[] args) {
        Class<Sub> clazz = Sub.class;

        // getFields：本类public + 父类public
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f.getName());
        }
        //输出： pubSub 、 pubBase

        // getDeclaredFields：只看Sub自己声明，父类一概不要
        Field[] declared = clazz.getDeclaredFields();
        for (Field f : declared) {
            System.out.println(f.getName());
        }
        //输出： pubSub 、 priSub
    }
}

class Base {
    public int pubBase;
    protected int proBase;
    private int priBase;
}

class Sub extends Base {
    public int pubSub;
    private int priSub;
}

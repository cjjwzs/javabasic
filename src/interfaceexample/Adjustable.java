package interfaceexample;

import java.util.HashMap;

public interface Adjustable {
    /**
     * 调节温度
     */
    public void adjust(int temperature);
}

class Base {
    private int speed;

    /**
     * 调节速度
     */
    public void adjust(int speed) {
        this.speed = speed;
    }
}

class Sub1 extends Base implements Adjustable {
    private int temperature;

    public void adjust(int temperature) {
        this.temperature = temperature;
    }

    public static void main(String[] args) {
        Sub1 sub1 = new Sub1();
        sub1.adjust(10);

        HashMap<String, String> map2 = new HashMap<String, String>() {}; // 匿名类!
        HashMap<String, String> map3 = new HashMap<String, String>() {
            {
                put("A", "1");
                put("B", "2");
            }
        };
        System.out.println(map3.get("A"));

    }
}

class Sub2 extends Base {
    private int temperature;

    private void adjustTemperature(int temperature) {
        this.temperature = temperature;
    }

    /**
     * 调节温度
     */
    private class Closure implements Adjustable {
        public void adjust(int temperature) {
            adjustTemperature(temperature);
        }
    }

    public Adjustable getCallBackReference() {
        return new Closure();
    }

    public static void main(String[] args) {
        Sub2 sub = new Sub2();
        Adjustable ad = sub.getCallBackReference();
        /**
         * 调节温度
         */
        ad.adjust(15);
        /**
         * 调节速度
         */
        sub.adjust(15);
    }
}


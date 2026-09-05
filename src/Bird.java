public class Bird {
    private String color = "blue";
    private int age = 222;

//    public Bird() {
//        super();
//    }

    public Bird(String color, int age) {
        this.color = color;
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Bird bird = new Bird("red", 10);
    }
}

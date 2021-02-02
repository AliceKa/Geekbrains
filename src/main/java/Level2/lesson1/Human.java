package Level2.lesson1;

public class Human {

    private int heightLimit;
    private int lengthLimit;
    private String name;

    public Human(){
    }

    public Human(String name, int heightLimit, int lengthLimit) {
        this.heightLimit = heightLimit;
        this.lengthLimit = lengthLimit;
        this.name = name;
    }

    public void setHeightLimit(int heightLimit) { this.heightLimit = heightLimit; }
    public int  getHeightLimit() { return heightLimit; }
    public void setLengthLimit(int lengthLimit) { this.lengthLimit = lengthLimit; }
    public int getLengthLimit() { return lengthLimit; }
    public void setName(String name) { this.name = name; }
    public String  getName() { return name; }

    public boolean run(int length) {
        if (length > lengthLimit) {
            return false;
        }
        return true;
    }

    public boolean jump(int height) {
        if (height > heightLimit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Human";
    }
}

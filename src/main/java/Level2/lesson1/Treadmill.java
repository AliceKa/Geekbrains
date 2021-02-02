package Level2.lesson1;

public class Treadmill {
    private int length;

    public void setLength(int length) { this.length = length; }
    public int  getLength() { return length; }

    public Treadmill() {
    }

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Treadmill";
    }
}

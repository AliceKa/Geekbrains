package Level2.lesson1;

public class Wall {

    private int height;

    public void setHeight(int height) { this.height = height; }
    public int  getHeight() { return height; }

    public Wall() {
    }

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Wall";
    }


}

public class Main {
    public static void main(String[] args) {

    }

}

class Robot {

}

class Grid {
    int xBoundary;
    int yBoundary;
    public Grid(int xBoundary, int yBoundary) {
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }
}

class TableTop extends Grid {
    public TableTop() {
        super(5, 5);
    }
}

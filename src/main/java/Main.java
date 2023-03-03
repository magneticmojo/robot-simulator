import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    }

}

// TODO how to connect 0,0 as SOUTH WEST?
// TODO Strategy pattern

class Simulation {
    TableTop tableTop;

    Simulation() {
        tableTop = new TableTop();
    }

}

class Robot {
    Position position;
    Direction facing;

}

class Position {
    int x;
    int y;
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


enum Direction {
    NORTH, SOUTH, EAST, WEST
}

// TODO Adapter pattern
class InputHandler {

    String filePath;

    public void x(String filePath) {
        this.filePath = filePath;
    }

    public void readInput() {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            if (file.exists()) {

                // TODO Parse input with scanner

            }

            // ???
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }


    }


}

//TODO --> Mottagning av textfil från källa||samma folder
//File file ....
//if file.exists() -->
//close resources...
//Scanner is not synchronized --> Can not share multiple threads



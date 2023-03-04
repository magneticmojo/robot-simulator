import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    // 'throws' clause added to indicate method trowing an exception --> Enable caller to handle exception appropriately
    public void readInput(String filePath) throws IllegalArgumentException, FileNotFoundException {
        if (filePath == null || filePath.isEmpty()) {
            throw new IllegalArgumentException("Invalid file path");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found " + filePath);
        }

        //Option to check if user has permition to read file with file.canRead()
        try (Scanner sc = new Scanner(filePath)) {


        }
    }


}

//TODO --> Mottagning av textfil från källa||samma folder
//File file ....
//if file.exists() -->
//close resources...
//Scanner is not synchronized --> Can not share multiple threads



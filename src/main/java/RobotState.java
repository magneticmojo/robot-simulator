public interface RobotState {

    void place(String[] args);

    void move();

    void left();

    void right();

    String report();

}

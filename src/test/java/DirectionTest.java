public class DirectionTest {

    public static void main(String[] args) {

        Direction direction = Direction.valueOf("NORTH");
        System.out.println("Initial " + direction);
        Direction d2 = direction.left();
        print(d2);
        System.out.println("Initial " + d2);
        Direction d3 = d2.left();
        print(d3);
        System.out.println("Initial " + d3);
        Direction d4 = d3.left();
        print(d4);
        System.out.println("Initial " + d4);

    }

    public static void print(Direction d) {
        for (int i = 0; i < d.values().length; i++) {
            System.out.println(d.values()[i]);
        }
    }
}

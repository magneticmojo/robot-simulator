// TODO public ?? --> Kan behöva ändra om jag ska testa klassen -->
record Grid(int xBoundary, int yBoundary) {
    public boolean positionInBounds(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return x >= 0 && x <= xBoundary && y >= 0 && y <= yBoundary;
    }
}
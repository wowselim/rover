package co.selim.rover;

public enum Direction {

    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    final char character;

    Direction(char character) {
        this.character = character;
    }
}

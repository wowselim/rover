package co.selim.rover;

import java.util.List;

public class Rover {

    private int x;
    private int y;
    private Direction direction;

    public Rover(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String move(List<Instruction> instructions) {
        doMove(instructions);
        return "(" + x + ", " + y + ") " + direction;
    }

    private void doMove(List<Instruction> instructions) {
        if (instructions.isEmpty()) return;

        var instruction = instructions.get(0);
        switch (instruction) {
            case FORWARD -> forward();
            case BACKWARD -> backward();
            case LEFT -> left();
            case RIGHT -> right();
        }

        doMove(instructions.subList(1, instructions.size()));
    }

    private void forward() {
        switch (direction) {
            case NORTH -> y++;
            case EAST -> x++;
            case SOUTH -> y--;
            case WEST -> x--;
        }
    }

    private void backward() {
        switch (direction) {
            case NORTH -> y--;
            case EAST -> x--;
            case SOUTH -> y++;
            case WEST -> x++;
        }
    }

    private void left() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case EAST -> direction = Direction.NORTH;
            case SOUTH -> direction = Direction.EAST;
            case WEST -> direction = Direction.SOUTH;
        }
    }

    private void right() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
    }
}

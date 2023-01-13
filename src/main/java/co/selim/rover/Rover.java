package co.selim.rover;

import java.util.List;
import java.util.function.Function;

import static java.util.function.Function.identity;

public class Rover {

    private int x;
    private int y;
    private Direction direction;
    private final List<Obstacle> obstacles;

    private enum State {READY, LANDED}

    private State currentState;

    private enum MovementState {CONTINUE, STOP}

    private MovementState movementState;

    public Rover(int x, int y, Direction direction, List<Obstacle> obstacles) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.obstacles = obstacles;
        this.currentState = State.READY;
    }

    public String land() {
        currentState = State.LANDED;
        return "Landed at (" + x + ", " + y + ") " + direction;
    }

    public String move(List<Instruction> instructions) {
        if (currentState != State.LANDED) throw new IllegalStateException("Rover hasn't landed yet");
        doMove(instructions);
        return "(" + x + ", " + y + ") " + direction + (movementState == MovementState.STOP ? " STOPPED" : "");
    }

    private void doMove(List<Instruction> instructions) {
        if (instructions.isEmpty()) return;

        var instruction = instructions.get(0);
        movementState = switch (instruction) {
            case FORWARD -> forward();
            case BACKWARD -> backward();
            case LEFT -> left();
            case RIGHT -> right();
        };

        if (movementState == MovementState.CONTINUE) {
            doMove(instructions.subList(1, instructions.size()));
        }
    }

    private MovementState forward() {
        return switch (direction) {
            case NORTH -> moveUp();
            case EAST -> moveRight();
            case SOUTH -> moveDown();
            case WEST -> moveLeft();
        };
    }

    private MovementState backward() {
        return switch (direction) {
            case NORTH -> moveDown();
            case EAST -> moveLeft();
            case SOUTH -> moveUp();
            case WEST -> moveRight();
        };
    }

    private MovementState move(Function<Integer, Integer> transformX, Function<Integer, Integer> transformY) {
        var newY = transformY.apply(y);
        var newX = transformX.apply(x);

        if (containsObstacle(newX, newY)) {
            return MovementState.STOP;
        } else {
            x = newX;
            y = newY;
            return MovementState.CONTINUE;
        }
    }

    private MovementState moveDown() {
        return move(identity(), oldY -> oldY - 1);
    }

    private MovementState moveUp() {
        return move(identity(), oldY -> oldY + 1);
    }

    private MovementState moveLeft() {
        return move(oldX -> oldX - 1, identity());
    }

    private MovementState moveRight() {
        return move(oldX -> oldX + 1, identity());
    }

    private boolean containsObstacle(int x, int y) {
        return obstacles.stream()
                .anyMatch(obstacle -> obstacle.x() == x && obstacle.y() == y);

    }

    private MovementState left() {
        switch (direction) {
            case NORTH -> direction = Direction.WEST;
            case EAST -> direction = Direction.NORTH;
            case SOUTH -> direction = Direction.EAST;
            case WEST -> direction = Direction.SOUTH;
        }
        return MovementState.CONTINUE;
    }

    private MovementState right() {
        switch (direction) {
            case NORTH -> direction = Direction.EAST;
            case EAST -> direction = Direction.SOUTH;
            case SOUTH -> direction = Direction.WEST;
            case WEST -> direction = Direction.NORTH;
        }
        return MovementState.CONTINUE;
    }
}

package co.selim.rover;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

    @Test
    void testSampleInstructions() {
        var rover = new Rover(0, 0, Direction.NORTH, emptyList());
        rover.land();
        var instructions = InstructionParser.parse("FFFLFFRBRF");
        var position = rover.move(instructions);
        assertEquals("(-1, 2) EAST", position);
    }

    @Test
    void testTurningRightWithoutMoving() {
        var rover = new Rover(0, 0, Direction.NORTH, emptyList());
        rover.land();
        var instructions = InstructionParser.parse("RR");
        var position = rover.move(instructions);
        assertEquals("(0, 0) SOUTH", position);
    }

    @Test
    void testTurningLeftWithoutMoving() {
        var rover = new Rover(0, 0, Direction.NORTH, emptyList());
        rover.land();
        var instructions = InstructionParser.parse("L");
        var position = rover.move(instructions);
        assertEquals("(0, 0) WEST", position);
    }

    @Test
    void testMovingForward() {
        var rover = new Rover(1, 1, Direction.NORTH, emptyList());
        rover.land();
        var instructions = InstructionParser.parse("FF");
        var position = rover.move(instructions);
        assertEquals("(1, 3) NORTH", position);
    }

    @Test
    void testMovingBackward() {
        var rover = new Rover(2, 1, Direction.EAST, emptyList());
        rover.land();
        var instructions = InstructionParser.parse("B");
        var position = rover.move(instructions);
        assertEquals("(1, 1) EAST", position);
    }

    @Test
    void testReportedLandingPosition() {
        var rover = new Rover(2, 1, Direction.EAST, emptyList());
        var position = rover.land();
        assertEquals("Landed at (2, 1) EAST", position);
    }

    @Test
    void testMovingWithoutLanding() {
        var rover = new Rover(2, 1, Direction.EAST, emptyList());
        var instructions = InstructionParser.parse("F");
        assertThrows(IllegalStateException.class, () -> {
            rover.move(instructions);
        });
    }

    @Test
    void testStopOnForwardXAxisCollision() {
        var obstacle = new Obstacle(3, 1);
        var rover = new Rover(2, 1, Direction.EAST, List.of(obstacle));
        rover.land();
        var instructions = InstructionParser.parse("F");
        var position = rover.move(instructions);
        assertEquals("(2, 1) EAST STOPPED", position);
    }

    @Test
    void testStopOnBackwardXAxisCollision() {
        var obstacle = new Obstacle(1, 1);
        var rover = new Rover(2, 1, Direction.EAST, List.of(obstacle));
        rover.land();
        var instructions = InstructionParser.parse("B");
        var position = rover.move(instructions);
        assertEquals("(2, 1) EAST STOPPED", position);
    }

    @Test
    void testStopOnYAxisBackwardCollision() {
        var obstacle = new Obstacle(2, 2);
        var rover = new Rover(2, 1, Direction.SOUTH, List.of(obstacle));
        rover.land();
        var instructions = InstructionParser.parse("B");
        var position = rover.move(instructions);
        assertEquals("(2, 1) SOUTH STOPPED", position);
    }

    @Test
    void testStopOnYAxisForwardCollision() {
        var obstacle = new Obstacle(2, 0);
        var rover = new Rover(2, 1, Direction.SOUTH, List.of(obstacle));
        rover.land();
        var instructions = InstructionParser.parse("F");
        var position = rover.move(instructions);
        assertEquals("(2, 1) SOUTH STOPPED", position);
    }
}

package co.selim.rover;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTest {

    @Test
    void testSampleInstructions() {
        var rover = new Rover(0, 0, Direction.NORTH);
        var instructions = InstructionParser.parse("FFFLFFRBRF");
        var position = rover.move(instructions);
        assertEquals("(-1, 2) EAST", position);
    }

    @Test
    void testTurningRightWithoutMoving() {
        var rover = new Rover(0, 0, Direction.NORTH);
        var instructions = InstructionParser.parse("RR");
        var position = rover.move(instructions);
        assertEquals("(0, 0) SOUTH", position);
    }

    @Test
    void testTurningLeftWithoutMoving() {
        var rover = new Rover(0, 0, Direction.NORTH);
        var instructions = InstructionParser.parse("L");
        var position = rover.move(instructions);
        assertEquals("(0, 0) WEST", position);
    }

    @Test
    void testMovingForward() {
        var rover = new Rover(1, 1, Direction.NORTH);
        var instructions = InstructionParser.parse("FF");
        var position = rover.move(instructions);
        assertEquals("(1, 3) NORTH", position);
    }

    @Test
    void testMovingBackward() {
        var rover = new Rover(2, 1, Direction.EAST);
        var instructions = InstructionParser.parse("B");
        var position = rover.move(instructions);
        assertEquals("(1, 1) EAST", position);
    }
}

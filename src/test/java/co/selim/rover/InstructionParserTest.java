package co.selim.rover;

import org.junit.jupiter.api.Test;

import java.util.List;

import static co.selim.rover.Instruction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InstructionParserTest {

    @Test
    void testSampleInstructions() {
        var expected = List.of(FORWARD, FORWARD, FORWARD, LEFT, FORWARD, FORWARD, RIGHT, BACKWARD, RIGHT, FORWARD);
        assertEquals(expected, InstructionParser.parse("FFFLFFRBRF"));
    }

    @Test
    void testForward() {
        assertEquals(List.of(FORWARD, FORWARD), InstructionParser.parse("FF"));
    }

    @Test
    void testBackward() {
        assertEquals(List.of(BACKWARD), InstructionParser.parse("B"));
    }

    @Test
    void testLeft() {
        assertEquals(List.of(LEFT, LEFT, LEFT), InstructionParser.parse("LLL"));
    }

    @Test
    void testRight() {
        assertEquals(List.of(RIGHT), InstructionParser.parse("R"));
    }

    @Test
    void testInvalidInstruction() {
        assertThrows(IllegalArgumentException.class, () -> {
            InstructionParser.parse("X");
        });
    }
}

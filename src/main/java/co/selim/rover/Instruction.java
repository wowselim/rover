package co.selim.rover;

import java.util.Arrays;

public enum Instruction {

    FORWARD('F'),
    BACKWARD('B'),
    LEFT('L'),
    RIGHT('R');

    final char character;

    Instruction(char character) {
        this.character = character;
    }

    static Instruction fromCharacter(char character) {
        return Arrays.stream(values())
                .filter(instruction -> instruction.character == character)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown instruction " + character));
    }
}

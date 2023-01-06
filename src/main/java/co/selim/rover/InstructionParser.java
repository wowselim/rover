package co.selim.rover;

import java.util.List;

public final class InstructionParser {

    private InstructionParser() {
    }

    public static List<Instruction> parse(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .map(Instruction::fromCharacter)
                .toList();

    }
}

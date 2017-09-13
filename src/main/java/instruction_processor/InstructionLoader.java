package instruction_processor;

import instruction_processor.exceptions.InstructionLoaderException;

import java.util.List;

public interface InstructionLoader {
    List<Instruction> loadInstructions() throws InstructionLoaderException;
}

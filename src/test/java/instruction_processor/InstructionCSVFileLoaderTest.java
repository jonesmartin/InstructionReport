package instruction_processor;

import instruction_processor.exceptions.BadlyFormedLineException;
import instruction_processor.exceptions.InstructionLoaderException;
import org.junit.Test;

import java.io.IOException;

public class InstructionCSVFileLoaderTest {

    @Test(expected = InstructionLoaderException.class)
    public void loadMissingFile() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_missing_instructions.csv", true).loadInstructions();
    }

    @Test(expected = InstructionLoaderException.class)
    public void loadBadlyFormedFile1() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_bad_instructions1.csv", true).loadInstructions();
    }

    @Test(expected = InstructionLoaderException.class)
    public void loadBadlyFormedFile2() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_bad_instructions2.csv", true).loadInstructions();
    }

    @Test(expected = InstructionLoaderException.class)
    public void loadBadlyFormedFile3() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_bad_instructions3.csv", true).loadInstructions();
    }

    @Test(expected = InstructionLoaderException.class)
    public void loadBadlyFormedFile4() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_bad_instructions4.csv", true).loadInstructions();
    }

    @Test(expected = InstructionLoaderException.class)
    public void loadBadlyFormedFile5() throws InstructionLoaderException {
        new InstructionCSVFileLoader("test_bad_instructions5.csv", true).loadInstructions();
    }
}

import InstructionExceptions.BadlyFormedLineException;
import org.junit.Test;

import java.io.IOException;

public class IngestInstructionsTest {

    @Test(expected = IOException.class)
    public void loadMissingFile() throws IOException, BadlyFormedLineException {
        IngestInstructions.importFile("test_missing_instructions.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile1() throws BadlyFormedLineException, IOException {
        IngestInstructions.importFile("test_bad_instructions1.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile2() throws BadlyFormedLineException, IOException {
        IngestInstructions.importFile("test_bad_instructions2.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile3() throws BadlyFormedLineException, IOException {
        IngestInstructions.importFile("test_bad_instructions3.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile4() throws BadlyFormedLineException, IOException {
        IngestInstructions.importFile("test_bad_instructions4.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile5() throws BadlyFormedLineException, IOException {
        IngestInstructions.importFile("test_bad_instructions5.csv");
    }
}

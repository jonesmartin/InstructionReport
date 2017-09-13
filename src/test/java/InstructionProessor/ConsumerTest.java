package InstructionProessor;

import InstructionProcesser.Consumer;
import InstructionProcesser.InstructionExceptions.BadlyFormedLineException;
import org.junit.Test;

import java.io.IOException;

public class ConsumerTest {

    @Test(expected = IOException.class)
    public void loadMissingFile() throws IOException, BadlyFormedLineException {
        Consumer.importFile("test_missing_instructions.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile1() throws BadlyFormedLineException, IOException {
        Consumer.importFile("test_bad_instructions1.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile2() throws BadlyFormedLineException, IOException {
        Consumer.importFile("test_bad_instructions2.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile3() throws BadlyFormedLineException, IOException {
        Consumer.importFile("test_bad_instructions3.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile4() throws BadlyFormedLineException, IOException {
        Consumer.importFile("test_bad_instructions4.csv");
    }

    @Test(expected = BadlyFormedLineException.class)
    public void loadBadlyFormedFile5() throws BadlyFormedLineException, IOException {
        Consumer.importFile("test_bad_instructions5.csv");
    }
}

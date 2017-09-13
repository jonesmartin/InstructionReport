package instruction_processor.exceptions;

public class InstructionLoaderException extends Exception {

    public InstructionLoaderException() {
        super();
    }

    public InstructionLoaderException(String message) {
        super(message);
    }

    public InstructionLoaderException(String message, Throwable chained) {
        super(message, chained);
    }

    public InstructionLoaderException(Throwable chained) {
        super(chained);
    }
}

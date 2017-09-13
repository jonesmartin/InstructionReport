package InstructionProcesser.InstructionExceptions;

public class BadlyFormedLineException extends Exception {

    public BadlyFormedLineException() {
        super();
    }

    public BadlyFormedLineException(String message) {
        super(message);
    }

    public BadlyFormedLineException(String message, Throwable chained) {
        super(message, chained);
    }

    public BadlyFormedLineException(Throwable chained) {
        super(chained);
    }
}

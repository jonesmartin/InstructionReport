package instruction_processor.exceptions;

public class MalformedCurrencyException extends Exception {
    public MalformedCurrencyException() {
        super();
    }

    public MalformedCurrencyException(String message) {
        super(message);
    }

    public MalformedCurrencyException(String message, Throwable chained) {
        super(message, chained);
    }

    public MalformedCurrencyException(Throwable chained) {
        super(chained);
    }
}

package InstructionProcesser;

import InstructionProcesser.InstructionExceptions.MalformedCurrencyException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class Currency {

    private String currencyCode;

    public Currency(String currencyCode) throws MalformedCurrencyException {
        if (currencyCode.length() != 3) {
            throw new MalformedCurrencyException();
        }
        this.currencyCode = currencyCode;
    }

    public LocalDate nextWorkDay(LocalDate settlement){
        switch (currencyCode) {
            case "AED":
            case "SAR":
                if (settlement.getDayOfWeek() == DayOfWeek.FRIDAY ||
                        settlement.getDayOfWeek() == DayOfWeek.SATURDAY)
                    settlement =  settlement.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            default:
                if (settlement.getDayOfWeek() == DayOfWeek.SATURDAY ||
                        settlement.getDayOfWeek() == DayOfWeek.SUNDAY)
                settlement = settlement.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        return settlement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        return currencyCode.equals(currency.currencyCode);
    }

    @Override
    public int hashCode() {
        return currencyCode.hashCode();
    }

    @Override
    public String toString() {
        return currencyCode;
    }
}

package instruction_processor;

import instruction_processor.exceptions.MalformedCurrencyException;
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

    /** Returns the LocalDate representing the next work day given the currency market
     *
     * @param date from which we roll forward to find the next work day if it isn't already a work day
     * @return the next work day taking into account the currency market
     */
    public LocalDate nextWorkDay(LocalDate date){
        switch (currencyCode) {
            case "AED":
            case "SAR":
                if (date.getDayOfWeek() == DayOfWeek.FRIDAY ||
                        date.getDayOfWeek() == DayOfWeek.SATURDAY)
                    date =  date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
                break;
            default:
                if (date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                        date.getDayOfWeek() == DayOfWeek.SUNDAY)
                date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        return date;
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

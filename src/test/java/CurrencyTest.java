import InstructionExceptions.MalformedCurrencyException;
import org.junit.Test;
import java.time.DayOfWeek;
import static org.junit.Assert.*;
import static utils.DateUtils.parseDate;

public class CurrencyTest {

    @Test(expected = MalformedCurrencyException.class)
    public void badCurrency1() throws MalformedCurrencyException {
        Currency ccy = new Currency("GBPP");
    }

    @Test(expected = MalformedCurrencyException.class)
    public void badCurrency2() throws MalformedCurrencyException {
        Currency ccy = new Currency("GB");
    }

    @Test
    public void goodCurrency() throws MalformedCurrencyException {
        Currency ccy = new Currency("GBP");
    }

    @Test
    public void validateNextWorkingDay() throws MalformedCurrencyException{
        Currency gbp = new Currency("GBP");
        Currency aed = new Currency("AED");
        Currency sar = new Currency("SAR");
        // Check Saturday rolls to Monday
        assertEquals(DayOfWeek.MONDAY,
                gbp.nextWorkDay(
                        parseDate("31 Dec 2016"))
                        .getDayOfWeek());
        // Check the Saturday date is rolls to Monday 2nd Jan
        assertEquals(parseDate("02 Jan 2017"),
                gbp.nextWorkDay(
                        parseDate("31 Dec 2016")));
        // Check Sunday rolls to Monday
        assertEquals(DayOfWeek.MONDAY,
                gbp.nextWorkDay(
                        parseDate("01 Jan 2017"))
                        .getDayOfWeek());
        // Check Monday stays as Monday
        assertEquals(DayOfWeek.MONDAY,
                gbp.nextWorkDay(
                        parseDate("02 Jan 2017"))
                        .getDayOfWeek());
        // Check Tuesday stays as Tuesday
        assertEquals(DayOfWeek.TUESDAY,
                gbp.nextWorkDay(
                        parseDate("03 Jan 2017"))
                        .getDayOfWeek());
        // Check Saturday rolls to Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        parseDate("31 Dec 2016"))
                        .getDayOfWeek());
        // Check Sunday stays as Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        parseDate("01 Jan 2017"))
                        .getDayOfWeek());
        // Check Friday rolls to Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        parseDate("30 Dec 2016"))
                        .getDayOfWeek());
        // Check Saturday rolls to Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        parseDate("31 Dec 2016"))
                        .getDayOfWeek());
        // Check Sunday stays as Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        parseDate("01 Jan 2017"))
                        .getDayOfWeek());
        // Check Friday rolls to Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        parseDate("30 Dec 2016"))
                        .getDayOfWeek());
    }
}

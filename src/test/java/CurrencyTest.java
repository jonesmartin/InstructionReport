import InstructionExceptions.MalformedCurrencyException;
import org.junit.Test;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;

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
                        LocalDate.parse("31 Dec 2016",
                        DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check the Saturday date is rolls to Monday 2nd Jan
        assertEquals(LocalDate.parse("02 Jan 2017",DateTimeFormatter.ofPattern("dd MMM yyyy")),
                gbp.nextWorkDay(
                        LocalDate.parse("31 Dec 2016",
                                DateTimeFormatter.ofPattern("dd MMM yyyy"))));
        // Check Sunday rolls to Monday
        assertEquals(DayOfWeek.MONDAY,
                gbp.nextWorkDay(
                        LocalDate.parse("01 Jan 2017",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Monday stays as Monday
        assertEquals(DayOfWeek.MONDAY,
                gbp.nextWorkDay(
                        LocalDate.parse("02 Jan 2017",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Tuesday stays as Tuesday
        assertEquals(DayOfWeek.TUESDAY,
                gbp.nextWorkDay(
                        LocalDate.parse("03 Jan 2017",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Saturday rolls to Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        LocalDate.parse("31 Dec 2016",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Sunday stays as Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        LocalDate.parse("01 Jan 2017",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Friday rolls to Sunday for SAR
        assertEquals(DayOfWeek.SUNDAY,
                sar.nextWorkDay(
                        LocalDate.parse("30 Dec 2016",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Saturday rolls to Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        LocalDate.parse("31 Dec 2016",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Sunday stays as Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        LocalDate.parse("01 Jan 2017",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
        // Check Friday rolls to Sunday for AED
        assertEquals(DayOfWeek.SUNDAY,
                aed.nextWorkDay(
                        LocalDate.parse("30 Dec 2016",
                                DateTimeFormatter.ofPattern("dd MMM yyyy")))
                        .getDayOfWeek());
    }
}

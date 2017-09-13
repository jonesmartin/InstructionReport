package InstructionProcesser;

import java.time.LocalDate;
import InstructionProcesser.utils.*;

public class Instruction {

    private String entity;
    private BuySellEnum buySell;
    private double agreedFX;
    private Currency currency;
    private LocalDate instructionDate; // assumption dates always received in local time
    private LocalDate settlementDate;
    private int units;
    private double unitPrice;
    private LocalDate adjustedSettlementDate;

    public Instruction(String entity, BuySellEnum buySell, double agreedFX, Currency currency,
                       LocalDate instructionDate, LocalDate settlementDate, int units,
                       double unitPrice) {
        this.entity = entity;
        this.buySell = buySell;
        this.agreedFX = agreedFX;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.units = units;
        this.unitPrice = unitPrice;
        this.adjustedSettlementDate = currency.nextWorkDay(settlementDate);
    }

    public String getEntity() {
        return entity;
    }

    public BuySellEnum getBuySell() {
        return buySell;
    }

    public double getAgreedFX() {
        return agreedFX;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public int getUnits() {
        return units;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getAmount() { return unitPrice * units * agreedFX; }

    public LocalDate getAdjustedSettlementDate() {
        return adjustedSettlementDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Instruction that = (Instruction) o;

        if (Double.compare(that.agreedFX, agreedFX) != 0) return false;
        if (units != that.units) return false;
        if (Double.compare(that.unitPrice, unitPrice) != 0) return false;
        if (!entity.equals(that.entity)) return false;
        if (buySell != that.buySell) return false;
        if (!currency.equals(that.currency)) return false;
        if (!instructionDate.equals(that.instructionDate)) return false;
        return settlementDate.equals(that.settlementDate);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = entity.hashCode();
        result = 31 * result + buySell.hashCode();
        temp = Double.doubleToLongBits(agreedFX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + currency.hashCode();
        result = 31 * result + instructionDate.hashCode();
        result = 31 * result + settlementDate.hashCode();
        result = 31 * result + units;
        temp = Double.doubleToLongBits(unitPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "InstructionProcesser.Instruction{" +
                "entity='" + entity + '\'' +
                ", buySell=" + buySell +
                ", agreedFX=" + agreedFX +
                ", currency=" + currency +
                ", instructionDate=" + instructionDate +
                ", settlementDate=" + settlementDate +
                ", units=" + units +
                ", unitPrice=" + unitPrice +
                ", adjustedSettlementDate=" + adjustedSettlementDate +
                '}';
    }
}

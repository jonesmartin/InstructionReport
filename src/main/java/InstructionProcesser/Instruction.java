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

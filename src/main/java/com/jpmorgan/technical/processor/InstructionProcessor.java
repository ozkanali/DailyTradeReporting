package com.jpmorgan.technical.processor;

import com.jpmorgan.technical.domain.Instruction;
import com.jpmorgan.technical.domain.Settlement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * This class processes the {@link Instruction} objects and calculates settlement values.
 */
public class InstructionProcessor {

    /**
     * Calculates the right settlement date for the given instruction values.
     *
     * @param currency       the currency code of the current instruction.
     * @param settlementDate the given settlement date.
     * @return calculated settlement date.
     */
    public Date calculatedSettlementDate(String currency, Date settlementDate) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(settlementDate);

            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY && (currency.equals("AED") || currency.equals("SAR"))) {
                calendar.add(Calendar.DAY_OF_YEAR, 2);
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                if (currency.equals("AED") || currency.equals("SAR")) {
                    calendar.add(Calendar.DAY_OF_YEAR, 1);
                } else {
                    calendar.add(Calendar.DAY_OF_YEAR, 2);
                }
            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !(currency.equals("AED") || currency.equals("SAR"))) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converst the given {@link Instruction} list to a list of {@link Settlement}.
     *
     * @param instructionList instructions to process.
     * @return a list of {@link Settlement}
     */
    public Collection<Settlement> processInstructions(Collection<Instruction> instructionList) {
        return instructionList.stream().map(instruction -> {
            Settlement settlement = new Settlement();
            settlement.setInstructionType(instruction.getInstructionType());
            settlement.setEntityName(instruction.getEntityName());
            settlement.setDate(calculatedSettlementDate(instruction.getCurrency(),
                    instruction.getSettlementDate()));
            settlement.setAmount(instruction.getAgreedFX() * instruction.getNumberOfUnits() * instruction.getPricePerUnit());
            return settlement;
        }).collect(Collectors.toList());
    }
}

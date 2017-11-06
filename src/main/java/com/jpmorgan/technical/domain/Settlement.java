package com.jpmorgan.technical.domain;

import java.util.Date;

/**
 * This class holds settlement calculation results like amount and date for {@link Instruction} object's.
 */
public class Settlement {

    private Date date;
    private Double amount;
    private InstructionType instructionType;
    private String entityName;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}

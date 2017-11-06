package com.jpmorgan.technical.domain;

import java.util.Date;

/**
 * This class represents each record in the trade operations.
 */
public class Instruction {

    private String entityName;
    private InstructionType instructionType;
    private Double agreedFX;
    private String currency;
    private Date instructionDate;
    private Date settlementDate;
    private int numberOfUnits;
    private Double pricePerUnit;
    private Double amountInUSD;

    private Instruction(String entityName, String instructionType, Double agreedFX, String currency, Date instructionDate,
                        Date settlementDate, int numberOfUnits, Double pricePerUnit) {
        this.entityName = entityName;
        this.instructionType = InstructionType.valueOf(instructionType);
        this.agreedFX = agreedFX;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.numberOfUnits = numberOfUnits;
        this.pricePerUnit = pricePerUnit;
    }


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(String instructionType) {
        this.instructionType = InstructionType.valueOf(instructionType);
    }

    public Double getAgreedFX() {
        return agreedFX;
    }

    public void setAgreedFX(Double agreedFX) {
        this.agreedFX = agreedFX;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Double getAmountInUSD() {
        return amountInUSD;
    }

    public void setAmountInUSD(Double amountInUSD) {
        this.amountInUSD = amountInUSD;
    }

    public static class InstructionBuilder {
        private String entityName;
        private String instructionType;
        private Double agreedFX;
        private String currency;
        private Date instructionDate;
        private Date settlementDate;
        private int numberOfUnits;
        private Double pricePerUnit;

        public InstructionBuilder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public InstructionBuilder instructionType(String instructionType) {
            this.instructionType = instructionType;
            return this;
        }

        public InstructionBuilder agreedFX(Double agreedFX) {
            this.agreedFX = agreedFX;
            return this;
        }

        public InstructionBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public InstructionBuilder instructionDate(Date instructionDate) {
            this.instructionDate = instructionDate;
            return this;
        }

        public InstructionBuilder settlementDate(Date settlementDate) {
            this.settlementDate = settlementDate;
            return this;
        }

        public InstructionBuilder numberOfUnits(int numberOfUnits) {
            this.numberOfUnits = numberOfUnits;
            return this;
        }

        public InstructionBuilder pricePerUnit(Double pricePerUnit) {
            this.pricePerUnit = pricePerUnit;
            return this;
        }

        public Instruction build() {
            return new Instruction(entityName, instructionType, agreedFX, currency, instructionDate, settlementDate, numberOfUnits, pricePerUnit);
        }
    }
}

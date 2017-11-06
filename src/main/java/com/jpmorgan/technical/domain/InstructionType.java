package com.jpmorgan.technical.domain;

/**
 * This enum represents the type of the instruction.
 */
public enum InstructionType {

    B("Buy"),
    S("Sell");

    private String type;

    InstructionType(String type) {
        this.type = type;
    }
}

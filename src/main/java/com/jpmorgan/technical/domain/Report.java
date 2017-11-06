package com.jpmorgan.technical.domain;

import java.util.*;

/**
 * this class represents the settlement report.
 */
public class Report {

    private Map<Date, Double> settledIncomingMap = new TreeMap<>();
    private Map<Date, Double> settledOutgoingMap = new TreeMap<>();
    private Map<String, Double> entityIncomingMap = new TreeMap<>();
    private Map<String, Double> entityOutgoingMap = new TreeMap<>();

    /**
     * Adds a settlement to the report.
     *
     * @param settlement settlement to add.
     */
    public void addSettlement(Settlement settlement) {
        Double totalValue;
        if (settlement.getInstructionType().equals(InstructionType.S)) {
            if (getSettledIncomingMap().containsKey(settlement.getDate())) {
                totalValue = getSettledIncomingMap().get(settlement.getDate()) + settlement.getAmount();
                getSettledIncomingMap().put(settlement.getDate(), totalValue);
            } else {
                getSettledIncomingMap().put(settlement.getDate(), settlement.getAmount());
            }

            if (getEntityIncomingMap().containsKey(settlement.getEntityName())) {
                totalValue = getEntityIncomingMap().get(settlement.getEntityName()) + settlement.getAmount();
                getEntityIncomingMap().put(settlement.getEntityName(), totalValue);
            } else {
                getEntityIncomingMap().put(settlement.getEntityName(), settlement.getAmount());
            }
        } else {
            if (getSettledOutgoingMap().containsKey(settlement.getDate())) {
                totalValue = getSettledOutgoingMap().get(settlement.getDate()) + settlement.getAmount();
                getSettledOutgoingMap().put(settlement.getDate(), totalValue);
            } else {
                getSettledOutgoingMap().put(settlement.getDate(), settlement.getAmount());
            }

            if (getEntityOutgoingMap().containsKey(settlement.getEntityName())) {
                totalValue = getEntityOutgoingMap().get(settlement.getEntityName()) + settlement.getAmount();
                getEntityOutgoingMap().put(settlement.getEntityName(), totalValue);
            } else {
                getEntityOutgoingMap().put(settlement.getEntityName(), settlement.getAmount());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        try {
            getSettledIncomingMap().entrySet().forEach(mapData -> {
                builder.append("Date : ").append(mapData.getKey()).append(" settled incoming: ")
                        .append(mapData.getValue()).append("/n");
            });
            getSettledOutgoingMap().entrySet().forEach(mapData -> {
                builder.append("Date : ").append(mapData.getKey()).append(" settled outgoing: ")
                        .append(mapData.getValue()).append("/n");
            });


            Map<String, Double> sortedEntityIncomingMap = sortByValueMap(getEntityIncomingMap());
            Map<String, Double> sortedEntityOutgoingMap = sortByValueMap(getEntityOutgoingMap());
            int ranking = 1;
            for (Map.Entry<String, Double> mapData : sortedEntityIncomingMap.entrySet()) {
                builder.append("Rank : ").append(ranking).append(" --> ").append(mapData.getKey())
                        .append(" incoming : ").append(mapData.getValue()).append("/n");
                ranking++;
            }
            ranking = 1;
            for (Map.Entry<String, Double> mapData : sortedEntityOutgoingMap.entrySet()) {
                builder.append("Rank : ").append(ranking).append(" --> ").append(mapData.getKey())
                        .append(" outgoing : ").append(mapData.getValue()).append("/n");
                ranking++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    /**
     * Sorts the given map according to ranking.
     *
     * @param map map to sort.
     * @return sorted map.
     */
    private Map<String, Double> sortByValueMap(Map<String, Double> map) {
        try {
            List<Map.Entry<String, Double>> list = new LinkedList<>(map.entrySet());
            Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

            Map<String, Double> result = new LinkedHashMap<>();
            for (Map.Entry<String, Double> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<Date, Double> getSettledIncomingMap() {
        return settledIncomingMap;
    }

    public Map<Date, Double> getSettledOutgoingMap() {
        return settledOutgoingMap;
    }

    public Map<String, Double> getEntityIncomingMap() {
        return entityIncomingMap;
    }

    public Map<String, Double> getEntityOutgoingMap() {
        return entityOutgoingMap;
    }
}

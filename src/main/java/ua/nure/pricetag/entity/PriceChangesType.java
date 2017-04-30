package ua.nure.pricetag.entity;


import java.math.BigInteger;
import java.util.stream.Stream;

public enum PriceChangesType {
    SUM(1, "+"), PERCENT(2, "%");

    private Integer key;
    private String value;

    PriceChangesType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static PriceChangesType getByKey(BigInteger key) {
        return Stream.of(PriceChangesType.values()).filter(pct -> pct.key.equals(key.intValue())).findFirst()
                .orElse(null);
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package ua.nure.pricetag.entity;

import java.util.stream.Stream;

public enum Role {
    USER(1, "USER"), ADMIN(2, "ADMIN");

    private Integer key;
    private String value;

    Role(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Role getByKey(Integer key) {
        return Stream.of(Role.values()).filter(role -> role.key.equals(key)).findFirst().orElse(null);
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

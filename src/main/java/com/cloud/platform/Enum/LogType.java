package com.cloud.platform.Enum;

public enum LogType {
    LOGIN(1),
    LOGOUT(2),
    NEW(3),
    MODIFY(4),
    DELETE(5);
    private int value;

    public int getValue() {
        return value;
    }

    LogType(int value) {
        this.value = value;
    }
}

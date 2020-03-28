package com.cloud.platform.Enum;

public enum ResponseType {
    SUCCESS(200),
    FAIL(400),
    WARN(300),
    ERROR(500);

    private int value;

    public int getValue() {
        return value;
    }

    ResponseType(int value) {
        this.value = value;
    }
}

package com.example.mybatisdemo.enums;

import java.util.Arrays;

public enum DataSourceType {
    MYSQL("mysql"),

    POSTGRESQL("postgresql");

    private final String type;

    DataSourceType(String type) {
        this.type = type;
    }

    /**
     * 获取数据源类型
     *
     * @param type type
     * @return DataSourceType
     */
    public static DataSourceType getDataSourceType(String type) {
        return Arrays.stream(DataSourceType.values()).
                filter(v -> v.type.equalsIgnoreCase(type)).
                findFirst().orElse(MYSQL);
    }
}

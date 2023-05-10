package com.example.mybatisdemo.context;

import com.example.mybatisdemo.enums.DataSourceType;

public class DataSourceContextHolder {
    /**
     * 数据源上下文
     */
    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源类型
     */
    public static void set(DataSourceType type) {
        contextHolder.set(type);
    }

    /**
     * 获取数据源类型
     *
     * @return DataSourceType
     */
    public static DataSourceType get() {
        return contextHolder.get();
    }

    /**
     * 使用MYSQL数据源
     */
    public static void mysql() {
        set(DataSourceType.MYSQL);
    }

    /**
     * 使用Postgresql数据源
     */
    public static void postgresql() {
        set(DataSourceType.POSTGRESQL);
    }

    public static void remove() {
        contextHolder.remove();
    }
}

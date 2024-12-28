package com.finalproject.util;

public class SnowflakeIdGenerator {

    private static final long EPOCH = 1620000000000L; // 自定义起始时间（比如某个时间点，避免溢出）
    private static final long SEQUENCE_BITS = 12L; // 序列号部分的位数
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS); // 序列号的最大值

    private long lastTimestamp = -1L; // 上一次生成ID的时间戳
    private long sequence = 0L; // 序列号
    private static final long SEQUENCE_MASK = MAX_SEQUENCE; // 序列号掩码

    // 生成ID的方法
    public synchronized String nextId() {
        long timestamp = System.currentTimeMillis() - EPOCH; // 当前时间戳减去自定义的起始时间

        if (timestamp == lastTimestamp) {
            // 如果是同一毫秒，序列号自增
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                // 如果序列号溢出，等待下一毫秒
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            // 如果时间戳不同，重置序列号
            sequence = 0;
        }

        lastTimestamp = timestamp;

        // 拼接成15位ID：时间戳(13位) + 序列号(2位)
        long id = (timestamp << SEQUENCE_BITS) | sequence;

        return String.valueOf(id);
    }

    // 等待下一毫秒
    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis() - EPOCH;
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis() - EPOCH;
        }
        return timestamp;
    }

}

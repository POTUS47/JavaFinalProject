package com.finalproject.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestResultExporter {
    private static final String CSV_HEADER = "用例编号,测试状态,返回消息,测试时间\n";
    private static final Path OUTPUT_PATH = Paths.get("test-results.csv");

    public static void exportResult(String testName, boolean isSuccess, String message) {
        try {
            // 如果是第一次写入，先写入表头
            if (!Files.exists(OUTPUT_PATH)) {
                Files.write(OUTPUT_PATH, CSV_HEADER.getBytes());
            }

            // 准备数据行
            String status = isSuccess ? "1" : "0";
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            String line = String.join(",",
                    escapeCsv(testName),
                    escapeCsv(status),
                    escapeCsv(message),
                    escapeCsv(timestamp)
            ) + "\n";

            // 追加写入文件
            Files.write(OUTPUT_PATH, line.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("无法写入测试结果文件: " + e.getMessage());
        }
    }

    private static String escapeCsv(String content) {
        if (content == null) return "";
        // 如果包含逗号或引号，用双引号包围
        if (content.contains(",") || content.contains("\"")) {
            return "\"" + content.replace("\"", "\"\"") + "\"";
        }
        return content;
    }
}
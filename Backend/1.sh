#!/bin/bash

# 定义变量
TARGET_DIR="javaProject/target"
JAR_FILE="FinalProject-0.0.1-SNAPSHOT.jar"
PORT=8080

# 进入目标目录
cd $TARGET_DIR || { echo "Failed to enter directory $TARGET_DIR"; exit 1; }

# 停止当前正在运行的后端服务（通过查找占用指定端口的进程）
echo "Stopping any existing backend service on port $PORT..."
PID=$(lsof -ti :$PORT)
if [ -n "$PID" ]; then
    kill -9 $PID
    echo "Process $PID using port $PORT has been terminated."
else
    echo "No process found using port $PORT."
fi

# 启动新的JAR文件
echo "Starting new backend service with JAR file: $JAR_FILE"
#nohup java -jar $JAR_FILE > /dev/null 2>&1 &
java -jar $JAR_FILE
echo "Backend service started."

# 输出日志信息到控制台（可选）
tail -f nohup.out
# 设置第一阶段的go 编译镜像
FROM openjdk:8-jdk-alpine

# 设置工作目录
WORKDIR /server

# 暴露应用程序的端口
EXPOSE 80

COPY blog/target/blog-1.0-SNAPSHOT.jar /server/blog.jar

# 设置启动命令
CMD ["java", "-jar", "blog.jar"]

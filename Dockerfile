# 设置第一阶段的go 编译镜像
FROM openjdk:17-jdk-alpine

# 设置工作目录
WORKDIR /server

# 暴露应用程序的端口
EXPOSE 80

COPY target/ThriveX-Server-0.0.1-SNAPSHOT.jar /server/blog.jar

# 设置启动命令
CMD ["java", "-Xms128m", "-Xmx256m", "-jar", "blog.jar"]

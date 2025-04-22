# 设置第一阶段的go 编译镜像
FROM openjdk:8-jdk-alpine
# 设置应用程序的网络端口配置
ENV PORT 80

# 配置数据库连接参数（数据库地址/端口、数据库名称）
ENV DB_PORT 3306
ENV DB_NAME ThriveX
ENV DB_HOST 127.0.0.1
ENV DB_USERNAME thrive
ENV DB_PASSWORD ThriveX@123?
ENV DB_INFO ${DB_HOST}:${DB_PORT}/${DB_NAME}

# 配置邮件服务器连接参数（SMTP服务器地址、端口及认证信息）
ENV EMAIL_HOST mail.qq.com
ENV EMAIL_PORT 465
ENV EMAIL_USERNAME 123456789@qq.com
ENV EMAIL_PASSWORD 123456789
ARG VERSION=2.5.2
ENV VERSION=${VERSION}

# 设置工作目录
WORKDIR /server

# 暴露应用程序的端口
EXPOSE ${PORT}

COPY blog/target/blog-1.0-SNAPSHOT.jar /server/blog.jar

# 设置启动命令
CMD ["java", "-jar", "blog.jar"]

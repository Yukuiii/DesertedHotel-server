FROM openjdk:21-jdk-slim

WORKDIR /app
RUN apt-get update && apt-get install -y tzdata

# 添加时区支持
ENV TZ=Asia/Shanghai

# 复制jar包
COPY target/*.jar app.jar

# 设置JVM参数
ENV JAVA_OPTS="-Xms256m -Xmx512m -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"] 
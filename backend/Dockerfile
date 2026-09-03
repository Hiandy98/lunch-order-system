# === 基礎依賴: 下載所有 Jar 包，避免重複下載 ===
FROM maven:3.9.6-eclipse-temurin-21-alpine AS base
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn dependency:go-offline -B

# === 開發環境: 支援 Spring Boot 熱重載 ===
FROM base AS development
COPY . .
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]

# === 生產環境基礎: 建立非 Root 的安全執行帳號 ===
FROM eclipse-temurin:21-jre-alpine AS production
WORKDIR /app
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# === 編譯打包: 利用 base 的依賴快取打包專案 ===
FROM base AS build
COPY . .
RUN --mount=type=cache,target=/root/.m2 \
    mvn clean package -DskipTests -B

# === 最終運行 ===
FROM production AS final
WORKDIR /app
COPY --from=build --chown=appuser:appgroup /app/target/*.jar app.jar
USER appuser
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]

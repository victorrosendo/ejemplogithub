# ============================================================
# ARCHIVO: ms-productos/Dockerfile
# DESCRIPCIÓN: Imagen Docker para el microservicio ms-productos.
# Misma estructura multi-stage que ms-usuarios.
# ============================================================

# ── STAGE 1: COMPILAR ────────────────────────────────────
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Cache de dependencias Maven
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Compilar
COPY src ./src
RUN mvn clean package -DskipTests

# ── STAGE 2: RUNTIME ─────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Puerto del microservicio de productos
EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]

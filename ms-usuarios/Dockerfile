# ============================================================
# ARCHIVO: ms-usuarios/Dockerfile
# DESCRIPCIÓN: Imagen Docker para el microservicio ms-usuarios.
# Usa multi-stage build:
#   Stage 1 (build)  → compila el JAR con Maven
#   Stage 2 (runtime) → imagen liviana solo con el JAR
# ============================================================

# ── STAGE 1: COMPILAR CON MAVEN ──────────────────────────
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar primero solo el pom.xml para aprovechar cache de Docker
# Si el pom no cambió, Docker reutiliza la capa de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente
COPY src ./src

# Compilar y empaquetar (omitir tests para acelerar el build)
RUN mvn clean package -DskipTests

# ── STAGE 2: IMAGEN DE EJECUCIÓN ─────────────────────────
# Imagen liviana de JRE (solo runtime, sin Maven ni JDK completo)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR generado en el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone el microservicio (definido en application.properties)
EXPOSE 8081

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]

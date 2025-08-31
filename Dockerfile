# Etapa 1: Construcción del JAR con Maven
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final, ligera con solo Java
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copiamos el JAR generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Puerto que expone este microservicio (cambia según el caso)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]

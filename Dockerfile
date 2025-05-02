# Use uma imagem base com Maven para construir a aplicação
FROM maven:3.8.7-openjdk-17 AS builder

# Defina o diretório de trabalho
WORKDIR /app

# Copie os arquivos pom.xml
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código fonte
COPY src .

# Construa a aplicação
RUN ./mvnw package -DskipTests

# Use uma imagem base JRE para execução
FROM eclipse-temurin:17-jre-focal

# Defina o diretório de trabalho
WORKDIR /app

# Copie o JAR construído
COPY --from=builder /app/target/*.jar app.jar

# Exponha a porta
EXPOSE 8080

# Execute a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
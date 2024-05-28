# Usar uma imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar o JAR da aplicação para o contêiner
COPY target/financas-1.0.0.jar app.jar

# Expor a porta da aplicação
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

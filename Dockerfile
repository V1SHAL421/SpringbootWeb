#Step 1: Create build artifact using an official Maven image
FROM maven:3.8.4-openjdk-17 as build

#Set working dir
WORKDIR /app

#Copy project files into working dir
COPY pom.xml .

#Fetch dependencies ensures Maven download all dependencies for project
#If pom.xml does not change, this step is cached
RUN mvn dependency:go-offline

COPY src /app/src/


#Package app
RUN mvn clean package -DskipTests

#Step 2: Create runtime container with OpenJDK image
FROM openjdk:17-slim

#Copy JAR from build stage (line 18) to create runtime container
COPY --from=build /app/target/*.jar /app/app.jar
# By using this multi-stage build, we separate build env from runtime env
WORKDIR /app
##Expose port the app runs on
EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]
##Command to run app
#CMD ["sh", "-c", "java -Dserver.port=$PORT -jar /app/app.jar"]
##sh -c ensures that the port env var is correctly interpreted at runtime

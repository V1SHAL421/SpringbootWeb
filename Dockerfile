#Step 1: Create build artifact using an official Maven image
FROM maven:3.8.4-openjdk-17 as build

#Copy project files into working dir
COPY pom.xml /app/
COPY src /app/src/

#Set working dir
WORKDIR /app
#Fetch dependencies ensures Maven download all dependencies for project
#If pom.xml does not change, this step is cached
RUN mvn dependency:go-offline

#Package app
RUN mvn clean package -DskipTests

#Step 2: Create runtime container with OpenJDK image
FROM openjdk:17-slim

#Copy JAR from build stage to create runtime container
COPY --from=build /app/target/*.jar /app/app.jar

##Expose port the app runs on
#EXPOSE 8080, we do not expose this because Heroku assigns a port dynamically

#Command to run app
CMD ["sh", "-c", "java -Dserver.port=$PORT -jar /app/app.jar"]
#sh -c ensures that the port env var is correctly interpreted at runtime
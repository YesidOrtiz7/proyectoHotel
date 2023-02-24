FROM openjdk:17-alpine
ADD "./hotel.jar" "hotel.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/hotel.jar"]
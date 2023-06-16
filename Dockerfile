FROM maven:3-amazoncorretto-17 as Builder

WORKDIR /app

COPY .mvn/ .mvn
COPY pom.xml ./
RUN mvn dependency:resolve

COPY src ./src

EXPOSE 8080

ENTRYPOINT ["mvn", "spring-boot:run"]
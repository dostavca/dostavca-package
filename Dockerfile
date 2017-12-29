FROM openjdk:8-jre-alpine

COPY packet/target /usr/src/dostavca/packet

WORKDIR /usr/src/dostavca/packet

EXPOSE 8080

CMD ["java", "-server", "-cp", "classes:dependency/*", "com.kumuluz.ee.EeApplication"]
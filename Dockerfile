FROM anapsix/alpine-java:8u202b08_jdk

MAINTAINER bhandari

RUN mkdir -p /opt/app

COPY build/libs/CreditScoreCompare-1.0.jar /opt/app/app.jar

EXPOSE 8090

WORKDIR /opt/app

CMD ["java", "-jar", "app.jar"]

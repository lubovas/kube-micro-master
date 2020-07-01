FROM openjdk:8-alpine
MAINTAINER lvasilev

EXPOSE 8080

RUN mkdir -p /usr/bin/kube-master
WORKDIR /usr/bin/kube-master

COPY /target/scala-2.13/kube-master.jar ./kube-master.jar

CMD ["java" ,"-jar" ,"kube-master.jar"]
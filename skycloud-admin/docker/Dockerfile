FROM java:8

MAINTAINER sky

ENV BOOT_ARGS=""

ADD skycloud-permission-1.0.0-SNAPSHOT.jar /home/ms-permission.jar

WORKDIR /home

CMD java ${BOOT_ARGS} -jar ms-permission.jar

EXPOSE  8104
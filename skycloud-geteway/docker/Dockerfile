FROM java:8

MAINTAINER sky

ENV BOOT_ARGS=""

ADD skycloud-geteway-1.0.0-SNAPSHOT.jar /home/ms-geteway.jar

WORKDIR /home

CMD java ${BOOT_ARGS} -jar ms-geteway.jar

EXPOSE  8201
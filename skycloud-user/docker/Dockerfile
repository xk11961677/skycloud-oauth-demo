FROM java:8

MAINTAINER sky

ENV BOOT_ARGS=""

ADD skycloud-user-1.0.0-SNAPSHOT.jar /home/ms-user.jar

WORKDIR /home

CMD java ${BOOT_ARGS} -jar ms-user.jar

EXPOSE  8102
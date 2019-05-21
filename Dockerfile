FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/project

# ADD .jar under target from host
# into this image
ADD target/selenium-docker.jar 			selenium-docker.jar
ADD target/selenium-docker-tests.jar 	selenium-docker-tests.jar
ADD target/libs							libs
ADD Data/TestData.properties    Data/TestData.properties

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well

# ADD suite files
ADD testng.xml					testng.xml

# ADD health check script
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh
 
# BROWSER
# HUB_HOST
# MODULE
ENTRYPOINT sh healthcheck.sh

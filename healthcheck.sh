#!/usr/bin/env bash
# Environment Variables
# HUB_HOST
# BROWSER
# MODULE


# start the java command
java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* \
    -DHUB_HOST=$HUB_HOST \
    -DBROWSER=$BROWSER \
    org.testng.TestNG $MODULE

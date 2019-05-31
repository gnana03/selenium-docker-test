# selenium-docker-test

Infrastructure Automation
Technical Design Document
Version	Date	Author	Rationale
0.1	24/05/2019	Gnana Lakshmi Kilambhi	Fist Draft


# 0	Preface
0.1	Purpose of this document
This document is a generic Technical Design Document which can be utilized across the projects in order to ease the Automation Environment setup in different operating systems. It provides guidance and template material which is intended to assist the infrastructure management. 
# 0.3	Overview
　　In today’s world continuous delivery is the challenging aspect in order to accelerate product delivery velocity,innovation and time to market.
　　In a product delivery life cycle Testing phase plays a major role to assess the quality of a product before it actually been delivered to the end customer. To speed up the delivery cycle and ensure the best quality of the product, automating the tests according to the customer use cases adds value to the product.
　　Based on several deciding factors the automation tools can be decided and executed. This document mainly concentrates on automating the environment setup to execute the automation tests easily and also it eases the upgradation of automation environment.
　　Goal is to containerize the test automation framework and create a disposable selenium infrastructure , create on demand and dispose when not required.
　　
# 0.3 PREREQUISITES
　　Install docker based on your operating system. After the successful installation of Docker verify below command by launching the command prompt /terminal 
　　docker --version.
　　If above command shows the installed docker version the installation is complete, If not please verify and re install.
　　Link to install docker based on Operating system : https://docs.docker.com/  
　　
　　
　　
　　
　　
　　
# 0.4 STEPS TO EXECUTE
# 0.4.1 Dckerfile and Jenkinsfile setup 
Place below lines in a blank file and save it as “Dockerfile”(name should as it is) in your respective project oustide of “src” folder.
FROM openjdk:8u191-jre-alpine3.8
RUN apk add curl jq

#Workspace
WORKDIR /usr/share/project

#ADD .jar under target from host
#into this image
ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs
ADD Data/TestData.properties Data/TestData.properties
ADD extent-config.xml extent-config.xml
#ADD healthcheck.sh healthcheck.sh
#in case of any other dependency like .csv / .json / .xls please ADD that as well

#ADD suite files
ADD testng.xml testng.xml

#ADD health check script
RUN wget https://s3.amazonaws.com/selenium-docker/healthcheck/healthcheck.sh

Create a file called Jenkinsfile (outside of src folder and where Dockerfile is placed) and place below lines inside it. ( For windows OS only) .
pipeline {
// master executor should be set to 0
agent any
stages {
stage('Build Jar') {
steps {
bat "mvn clean package -DskipTests"
}
}
stage('Build Image') {
steps {
withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
bat "docker build -t ${user}/selenium-docker ."
}
}
}
stage('Push Image') {
steps {
withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
bat "docker login --username=${user} --password=${pass}"
bat "docker push ${user}/selenium-docker:latest"
}
}
}
}
}

If the Operating system is Linux

Pipeline{
// master executor should be set to 0
agent any
stages {
stage('Build Jar') {
steps {
sh "mvn clean package -DskipTests"
}
}
stage('Build Image') {
steps {
docker.withRegistry('https://registry.hub.docker.com', 'dockerhub'){
sh "docker build -t ${user}/selenium-docker ."
}
}
}
stage('Push Image') {
steps {
docker.withRegistry('https://registry.hub.docker.com', 'dockerhub')
sh "docker login --username=${user} --password=${pass}"
sh"docker push ${user}/selenium-docker:latest"
}
}
}
}
}


# 0.4.2 modification of pom.xml 
　　Include below lines inside pom.xml
<dependencies>
<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>3.14.0</version>
</dependency>
<dependency>
<groupId>org.testng</groupId>
<artifactId>testng</artifactId>
<version>6.14.3</version>
<scope>test</scope>
</dependency>
</dependencies>
<build>
<finalName>selenium-docker</finalName>
<plugins>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-compiler-plugin</artifactId>
<version>3.7.0</version>
<configuration>
<compilerVersion>1.8</compilerVersion>
<source>1.8</source>
<target>1.8</target>
<testSource>1.8</testSource>
<testTarget>1.8</testTarget>
</configuration>
</plugin>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-dependency-plugin</artifactId>
<executions>
<execution>
<id>copy-dependencies</id>
<phase>prepare-package</phase>
<goals>
<goal>copy-dependencies</goal>
</goals>
<configuration>
<outputDirectory>
${project.build.directory}/libs
</outputDirectory>
</configuration>
</execution>
</executions>
</plugin>
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-jar-plugin</artifactId>
<version>3.1.0</version>
<executions>
<execution>
<goals>
<goal>test-jar</goal>
</goals>
</execution>
</executions>
</plugin>
</plugins>
</build>
　　
　　
# 0.4.2 Installing Jenkins using Docker 
Execute below commands in a remote server where the tests to be executed on jenkins. 
　　docker pull jenkins/jenkins:lts
　　docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts .
Navigate through localhost:8080 and setup the password followed by default plugins.

# 0.4.3 GitHub Hooks configuration
 	 In settings of the project create a webhook with jenkins url as in below. (provide complete URL where jenkins is running instead pf localhost)
　　
# 0.4.3 Creation of Jenkins jobs
# 0.4.3.1 SELENIUM-DOCKER-BUILDER
　　Create a new job called SELENIUM-DOCKER-BUILDER pipeline and provide the github project details along with the jenkinsfile and pollSCM with cran form of  “* * * * *” to poll every minute and trigger the build on push request.
　　Provide pipeline script from SCM amd place the complete path with credentials being added.
# 0.4.3.2 TEST_EXECUTOR
　　Place below files in another github project (Jenkinsfile and docker-compose.yml).
　　Edit the below contents inside Jenkinsfile
pipeline{
agent any
stages{
stage("Pull Latest Image"){
steps{
withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'pass', usernameVariable: 'user')]) {
bat "docker pull ${user}/selenium-docker"
}
}
}

stage("Run Grid"){
steps{
bat "docker-compose up -d hub chrome firefox"
}
}

stage("Run Test in chrome"){
steps{
bat "docker-compose up testng-chrome"
}
}
stage("Run Test in firefox"){
steps{
bat "docker-compose up testng-firefox"
}
}
stage("Stop grid"){
steps{
bat "docker-compose down"
}
}
}
}
　　
Contents of docker-compose.yml file contents are :
version: "3"
services:
hub:
image: selenium/hub:3.14
ports:
- "4444:4444"
chrome:
image: selenium/node-chrome:3.14
depends_on:
- hub
environment:
- HUB_HOST=hub
firefox:
image: selenium/node-firefox:3.14
shm_size: 1gb
depends_on:
- hub
environment:
- HUB_HOST=hub
testng-firefox:
image: gnana3/selenium-docker
environment:
- BROWSER=firefox
- HUB_HOST=hub
- MODULE=testng.xml
volumes:
- ./results-output-firefox:/usr/share/project/test-output
- ./extent-reports-firefox:/usr/share/project/ExtentReports
testng-chrome:
image: gnana3/selenium-docker
environment:
- BROWSER=chrome
- HUB_HOST=hub
- MODULE=testng.xml
volumes:
- ./results-output-chrome:/usr/share/project/test-output
- ./extent-reports-chrome:/usr/share/project/ExtentReports

Create a pipeline job called TEST_EXECUTOR in jenkins and place the above jenkinsfile from github.
Create a dependancy of SELENIUM-DOCKER-BUILDER job with TEST_EXECUTOR job so that SELENIUM-DOCKER-BUILDER is executed first followed by TEST_EXECUTOR in jenkins.

# Note : This can be integrated with AWS and Zalenium 

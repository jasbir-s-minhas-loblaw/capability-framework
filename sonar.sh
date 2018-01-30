#!/bin/bash
DATE=`date '+%Y-%m-%dT%H:%M:%S%z'`
VERSION=`date '+%Y-%m-%d_%H-%M-%S'`
echo $DATE
mvn clean install  -Dplugin=org.jacoco:jacoco-maven-plugin -Ddetail
mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.projectVersion=$VERSION -Dsonar.projectDate=$DATE

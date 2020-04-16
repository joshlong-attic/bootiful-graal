#!/usr/bin/env bash


mvn -DskipTests=true clean package
export MI=src/main/resources/META-INF
mkdir -p $MI
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar ./target/vanilla-jpa-0.0.1.BUILD-SNAPSHOT.jar
tree $MI
mvn -Pgraal clean package

#
#BLUE='\033[0;34m'
#NC='\033[0m'
#
#printf "=== ${BLUE}Building %s sample${NC} ===\n" "${PWD##*/}"
#./compile.sh && ${PWD%/*samples/*}/scripts/test.sh

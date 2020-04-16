mvn -DskipTests=true clean package
export MI=src/main/resources/META-INF
mkdir -p $MI 

java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/traditional.jar
## curl localhost:8080/reservations  !! 
tree $MI
mvn -Pgraal clean package


# mvn -Pgraal clean package

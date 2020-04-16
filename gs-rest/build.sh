
mvn -DskipTests=true clean package


## once u run the next line, the program will block. u need to exercise the endpoints u want to work. kinda cool! u need to use good Ci to make things faster! 
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar ./target/rest-service-0.0.1-SNAPSHOT.jar


mvn -Pgraal clean package
#mvn clean package
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/traditional.jar
mvn -Pgraal clean package

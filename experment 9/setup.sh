#!/bin/bash
set -e
echo "Running setup.sh - will download jars and compile the three example projects."

# helper function
dl() {
  url="$1"
  out="$2"
  if [ ! -f "$out" ]; then
    echo "Downloading $url -> $out"
    wget -q --show-progress "$url" -O "$out"
  else
    echo "Already have $out"
  fi
}

# create lib folders and download minimal required jars from Maven Central
BASEURL="https://repo1.maven.org/maven2"

# Part A (Spring DI) - need spring-context and its dependencies
mkdir -p part-a-spring-di/lib
dl "${BASEURL}/org/springframework/spring-context/5.3.29/spring-context-5.3.29.jar" part-a-spring-di/lib/spring-context-5.3.29.jar
dl "${BASEURL}/org/springframework/spring-beans/5.3.29/spring-beans-5.3.29.jar" part-a-spring-di/lib/spring-beans-5.3.29.jar
dl "${BASEURL}/org/springframework/spring-core/5.3.29/spring-core-5.3.29.jar" part-a-spring-di/lib/spring-core-5.3.29.jar
dl "${BASEURL}/org/springframework/spring-jcl/5.3.29/spring-jcl-5.3.29.jar" part-a-spring-di/lib/spring-jcl-5.3.29.jar

# Part B (Hibernate CRUD)
mkdir -p part-b-hibernate-crud/lib
dl "${BASEURL}/org/hibernate/hibernate-core/5.6.15.Final/hibernate-core-5.6.15.Final.jar" part-b-hibernate-crud/lib/hibernate-core-5.6.15.Final.jar
dl "${BASEURL}/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar" part-b-hibernate-crud/lib/mysql-connector-java-8.0.33.jar
# add small dependencies used by Hibernate (slf4j simple)
dl "${BASEURL}/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar" part-b-hibernate-crud/lib/slf4j-api-1.7.36.jar
dl "${BASEURL}/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar" part-b-hibernate-crud/lib/slf4j-simple-1.7.36.jar

# Part C (Spring + Hibernate banking)
mkdir -p part-c-spring-hibernate-bank/lib
dl "${BASEURL}/org/springframework/spring-context/5.3.29/spring-context-5.3.29.jar" part-c-spring-hibernate-bank/lib/spring-context-5.3.29.jar
dl "${BASEURL}/org/springframework/spring-tx/5.3.29/spring-tx-5.3.29.jar" part-c-spring-hibernate-bank/lib/spring-tx-5.3.29.jar
dl "${BASEURL}/org/hibernate/hibernate-core/5.6.15.Final/hibernate-core-5.6.15.Final.jar" part-c-spring-hibernate-bank/lib/hibernate-core-5.6.15.Final.jar
dl "${BASEURL}/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar" part-c-spring-hibernate-bank/lib/mysql-connector-java-8.0.33.jar
dl "${BASEURL}/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar" part-c-spring-hibernate-bank/lib/slf4j-api-1.7.36.jar
dl "${BASEURL}/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar" part-c-spring-hibernate-bank/lib/slf4j-simple-1.7.36.jar

echo "Compiling Java sources..."

# compile part A
mkdir -p part-a-spring-di/classes
javac -cp "part-a-spring-di/lib/*" -d part-a-spring-di/classes part-a-spring-di/src/com/example/parta/*.java

# compile part B
mkdir -p part-b-hibernate-crud/classes
javac -cp "part-b-hibernate-crud/lib/*" -d part-b-hibernate-crud/classes part-b-hibernate-crud/src/com/example/partb/*.java

# compile part C
mkdir -p part-c-spring-hibernate-bank/classes
javac -cp "part-c-spring-hibernate-bank/lib/*:part-c-spring-hibernate-bank/classes" -d part-c-spring-hibernate-bank/classes part-c-spring-hibernate-bank/src/com/example/partc/*.java

echo "Done. Use README.md for next steps."

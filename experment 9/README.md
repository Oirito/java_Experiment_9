
spring + hibernate assignment
============================

This zip contains three example Java projects:
- part-a-spring-di : Spring DI using Java @Configuration
- part-b-hibernate-crud : Hibernate CRUD example (console)
- part-c-spring-hibernate-bank : Spring + Hibernate transaction example (console)

Each part has a `setup.sh` at the root which will download required jars and compile the code.
Workflow (on Nimbus / Linux):

1. Unzip and cd into the project root:
   unzip spring_hibernate_assignment.zip -d workdir
   cd workdir

2. Make setup executable and run it:
   chmod +x setup.sh
   ./setup.sh

   The script will download required jars into lib/ folders and compile sources.
   It does NOT start any DB. You must run MySQL and create databases as described below.

Databases (create using mysql client):
- part-b-hibernate-crud: database `hibernate_demo` with table `student(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), course VARCHAR(50))`
- part-c-spring-hibernate-bank: database `bankdb` with table `account(id INT PRIMARY KEY, name VARCHAR(50), balance DOUBLE)`

JDBC credentials assumed: root / (empty password). Edit source/config to change credentials.


Kermalendario
============================

Run the following command to build everything and enhance the DB classes:

      mvn clean compile

OR 

Run the following command to compile all classes and launch the unit tests:

      mvn test

THEN:

Make sure that the database was correctly configured. Use the contents of the file *kermalendarioBD.sql* to create the database and grant privileges. For example,

      mysql –uroot -p < sql/kermalendarioBD.sql

Execute the following command to enhance the database classes.

      mvn datanucleus:enhance

Run the following command to create database schema for this sample.

      mvn datanucleus:schema-create

To launch the server run the command

    mvn jetty:run

Now, the client sample code can be executed in a new command window with

    mvn exec:java -Pclient


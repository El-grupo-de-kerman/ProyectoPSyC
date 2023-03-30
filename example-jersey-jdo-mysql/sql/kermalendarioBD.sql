/* DELETE 'messagesDB' database*/
DROP SCHEMA IF EXISTS kermalendarioDB;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'localhost';

/* CREATE 'kermalendarioDB' DATABASE */
CREATE SCHEMA kermalendarioDB;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'localhost' IDENTIFIED BY 'spq';

GRANT ALL ON kermalendarioDB.* TO 'spq'@'localhost';
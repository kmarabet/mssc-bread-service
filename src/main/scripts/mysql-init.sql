DROP DATABASE IF EXISTS breadservice;
DROP USER IF EXISTS `bread_service`@`%`;
CREATE DATABASE IF NOT EXISTS breadservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `bread_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `breadservice`.* TO `bread_service`@`%`;
FLUSH PRIVILEGES;
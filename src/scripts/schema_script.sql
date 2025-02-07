CREATE DATABASE MedicineDB;
USE MedicineDB;

CREATE TABLE Medicine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    manufacturer VARCHAR(100),
    price DECIMAL(10, 2),
    quantity INT
);

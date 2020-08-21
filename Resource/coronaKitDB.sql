DROP DATABASE coronaKitDB;

CREATE DATABASE coronaKitDB;

use coronaKitDB;

CREATE TABLE coronaKit(
 Item_Id INT PRIMARY KEY,
 Item_Name varchar(25) NOT NULL,
 Item_Desc varchar(30) NOT NULL,
 Quantity BIGINT NOT NULL,
 Price BIGINT NOT NULL
);
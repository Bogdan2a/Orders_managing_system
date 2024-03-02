package org.example;

import businessLayer.ClientBLL;
import businessLayer.ProductBLL;
import dataAccessLayer.AbstractDAO;
import model.Client;
import model.Product;
import presentation.Controller;
import presentation.Menu;

import java.sql.SQLException;

/**
 * The main class that serves as the entry point for the application.
 * It initializes the necessary components and starts the execution of the program.
 */
public class App {

    public static void main(String[] args) throws SQLException {

        Menu menu = new Menu();
        menu.setVisible(true);
        Controller controller = new Controller(menu);
    }
}

/*

-- Table for clients
drop table if exists assignment3.client cascade;
CREATE TABLE assignment3.client (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

-- Table for products
drop table if exists assignment3.product cascade;
CREATE TABLE assignment3.product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    price INT,
    stock INT
);

-- Table for orders
drop table if exists assignment3.order cascade;
CREATE TABLE assignment3.order (
    id SERIAL PRIMARY KEY,
    clientid INT REFERENCES assignment3.client(id),
    productid INT REFERENCES assignment3.product(id),
    quantity INT,
    total_price INT
);

-- Table for bills
drop table if exists assignment3.bill cascade;
CREATE TABLE assignment3.bill (
    id SERIAL PRIMARY KEY,
    orderid INT REFERENCES assignment3.order(id),
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price INT
);

*/

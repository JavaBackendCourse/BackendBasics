package org.lessons.lesson2.rest;

import com.sun.net.httpserver.HttpServer;
import org.lessons.lesson2.rest.handlers.CustomersHandler;
import org.lessons.lesson2.rest.handlers.ProductsHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class RestExample {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.setExecutor(Executors.newFixedThreadPool(10));
            server.createContext("/products", new ProductsHandler());
            server.createContext("/customers", new CustomersHandler());
            server.start();
            System.out.println("Сервер успешно запустился по адресу http://localhost:8080");
        } catch (Exception e) {
            System.out.println("Ошибка при запуске сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

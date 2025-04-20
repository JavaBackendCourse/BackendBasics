package org.lessons.lesson3.weather_app;

import com.sun.net.httpserver.HttpServer;
import org.lessons.lesson3.weather_app.handler.WeatherHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class WeatherApp {
    private static final Logger log = LoggerFactory.getLogger(WeatherApp.class);

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/weather", new WeatherHandler());
            server.setExecutor(null);
            server.start();

            log.info("Сервер поднят по адресу http://localhost:8080!");
        } catch (Exception e) {
            log.info("Непредвиденная ошибка при запуске приложения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

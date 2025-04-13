package org.lessons.lesson2.rest.handlers;

import com.fasterxml.jackson.core.JsonParseException;
import com.sun.net.httpserver.HttpExchange;
import org.lessons.lesson1.databasebasics.example1.config.DbConfig;
import org.lessons.lesson1.databasebasics.example1.models.Product;
import org.lessons.lesson1.databasebasics.example1.repository.ProductsRepository;
import org.lessons.lesson2.rest.util.converters.JsonConverter;
import org.lessons.lesson2.rest.util.http.ContentTypes;
import org.lessons.lesson2.rest.util.http.HttpStatus;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ProductsHandler extends BaseHandler {
    private final ProductsRepository productsRepository = new ProductsRepository();

    @Override
    public void handle(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        String contentType = getContentType(exchange);

        try {
            if (method.equalsIgnoreCase("GET")) {
                handleGet(exchange, contentType);
            } else if (method.equalsIgnoreCase("POST")) {
                handlePost(exchange, contentType);
            } else {
                sendResponse(exchange, HttpStatus.METHOD_NOT_ALLOWED.getCode(), "Метод не поддерживается", Optional.empty());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void handleGet(HttpExchange exchange, String contentType) throws IOException {
        try (Connection conn = DbConfig.getConnection()) {
            String path = exchange.getRequestURI().getPath();
            String[] paths = path.split("/");

            if (paths.length == 2) {
                // /products - все продукты
                handleGetAll(conn, exchange, contentType);
            } else if (paths.length == 3) {
                // /products/{id} - вернуть продукт по id
                Long id = Long.parseLong(paths[2]);
                handleGetById(conn, exchange, id, contentType);
            } else {
                sendResponse(exchange,
                        HttpStatus.BAD_REQUEST.getCode(), "Неверный путь запроса", Optional.empty());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.getCode(),
                    "Ошибка сервера: " + e.getMessage(), Optional.empty());
        }
    }

    private void handleGetAll(Connection conn, HttpExchange exchange, String contentType) throws IOException {
        try {
            List<Product> products = productsRepository.getAllProducts(conn);
            if (contentType.equalsIgnoreCase(ContentTypes.APPLICATION_JSON.getValue())) {
                sendResponse(exchange, HttpStatus.OK.getCode(),
                        JsonConverter.toJson(products).orElse("{}"),
                        Optional.of(contentType)
                );
            } else {
                sendResponse(exchange,
                        HttpStatus.BAD_REQUEST.getCode(),
                        "Неподдерживаемый Content-Type", Optional.empty());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.getCode(),
                    "Ошибка сервера: " + e.getMessage(), Optional.empty());
        }
    }

    private void handleGetById(Connection conn, HttpExchange exchange, Long id, String contentType) throws IOException {
        try {
            Optional<Product> productOpt = productsRepository.getProductById(conn, id);

            if (contentType.equalsIgnoreCase(ContentTypes.APPLICATION_JSON.getValue())) {
                if (productOpt.isPresent()) {
                    sendResponse(exchange, HttpStatus.OK.getCode(),
                            JsonConverter.toJson(productOpt.get()).orElse("{}"),
                            Optional.of(contentType)
                    );
                }
            } else {
                sendResponse(exchange,
                        HttpStatus.BAD_REQUEST.getCode(),
                        "Неподдерживаемый Content-Type!", Optional.empty());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.getCode(),
                    "Ошибка сервера: " + e.getMessage(), Optional.empty());
        }
    }

    private void handlePost(HttpExchange exchange, String contentType) throws IOException {
        try (Connection conn = DbConfig.getConnection()) {
            String requestBodyJson = new String(exchange.getRequestBody().readAllBytes());

            if (contentType.equalsIgnoreCase(ContentTypes.APPLICATION_JSON.getValue())) {
                Product product = JsonConverter.toObject(requestBodyJson, Product.class)
                        .orElseThrow(
                                () -> new JsonParseException("Конвертация из json прошла неуспешно!")
                        );

                productsRepository.addNewProductSafe(conn, product);

                sendResponse(exchange, HttpStatus.CREATED.getCode(), "Продукт успешно добавлен!", Optional.empty());
            } else {
                sendResponse(exchange,
                        HttpStatus.BAD_REQUEST.getCode(),
                        "Неподдерживаемый Content-Type!", Optional.empty());
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR.getCode(),
                    "Ошибка сервера: " + e.getMessage(), Optional.empty());
        }
    }


    private String getContentType(HttpExchange exchange) {
        return exchange.getRequestHeaders().getFirst("Content-Type");
    }
}

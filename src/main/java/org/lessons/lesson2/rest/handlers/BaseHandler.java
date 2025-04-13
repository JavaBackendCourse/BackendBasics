package org.lessons.lesson2.rest.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public abstract class BaseHandler implements HttpHandler {
    protected void sendResponse(HttpExchange exchange, int statusCode, String response, Optional<String> contentType) throws IOException {
        byte[] responseBytes = response.getBytes(StandardCharsets.UTF_8);

        contentType.ifPresent(s -> exchange.getResponseHeaders().add("Content-Type", s + "; charset=UTF-8"));

        exchange.sendResponseHeaders(statusCode, responseBytes.length);

        try (exchange; OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }
}

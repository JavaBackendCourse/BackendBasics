package org.lessons.lesson2.rest.util.http;

public enum ContentTypes {
    APPLICATION_JSON("application/json"),
    APPLICATION_XML("application/xml");

    private final String value;

    ContentTypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}

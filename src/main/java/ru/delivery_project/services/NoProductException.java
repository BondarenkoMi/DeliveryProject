package ru.delivery_project.services;

public class NoProductException extends Exception {
    public NoProductException(String message) {
        super(message);
    }
}

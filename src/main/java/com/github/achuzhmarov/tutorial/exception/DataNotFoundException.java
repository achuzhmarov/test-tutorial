package com.github.achuzhmarov.tutorial.exception;

public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String entityName, Long id){
        super(entityName + " with id=" + id + " not found");
    }

    public DataNotFoundException(String entityName, String name){
        super(entityName + " with name=" + name + " not found");
    }
}


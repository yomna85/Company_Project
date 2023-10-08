package com.example.BackendTask.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(generateMessage(resourceName, id.toString()));
    }

    public ResourceNotFoundException(String resourceName, String key) {
        super(generateMessage(resourceName, key));
    }


    private static String generateMessage(String resourceName, String key) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With Key = ").append(key).append(" Not Found.");
        return messageBuilder.toString();
    }

    public ResourceNotFoundException(String resourceName, String key, String value) {
        super(generateMessage(resourceName, key, value));
    }

    private static String generateMessage(String resourceName, String key, String value) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With ").append(key).append(" = ").append(value).append(" Not Found.");
        return messageBuilder.toString();
    }

}

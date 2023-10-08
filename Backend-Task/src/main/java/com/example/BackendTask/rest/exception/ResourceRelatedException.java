package com.example.BackendTask.rest.exception;

public class ResourceRelatedException extends RuntimeException {
    private ErrorCodes code;

    public ResourceRelatedException(String resourceName, String key, String value, ErrorCodes code) {
        super(generateMessage(resourceName, key, value));
        this.code = code;
    }

    private static String generateMessage(String resourceName, String key, String value) {
        StringBuilder messageBuilder = new StringBuilder(resourceName).
                append(" Resource With ").append(key).append(" = ").append(value).append(" Has Relations.");
        return messageBuilder.toString();
    }

    public ErrorCodes getCode() {
        return this.code;
    }
}

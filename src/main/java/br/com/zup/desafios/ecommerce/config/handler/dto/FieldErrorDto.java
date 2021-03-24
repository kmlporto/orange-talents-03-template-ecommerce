package br.com.zup.desafios.ecommerce.config.handler.dto;

public class FieldErrorDto {
    private String field;
    private String message;

    public FieldErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }
}

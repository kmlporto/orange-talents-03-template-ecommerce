package br.com.zup.desafios.ecommerce.config.handler.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDto {
    private List<String> globalErrorMessages = new ArrayList<>();
    private List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public void addError(String message){
        globalErrorMessages.add(message);
    }

    public void addFieldError(String field, String message){
        FieldErrorDto fieldError = new FieldErrorDto(field, message);
        fieldErrors.add(fieldError);
    }

    public List<String> getGlobalErrorMessages() {
        return globalErrorMessages;
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}

package pl.piegoose.songify.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorHandlerResponseDto(List<String> errors, HttpStatus status) {
}

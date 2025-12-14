package pl.piegoose.songify.infrastructure.apivalidation;

import java.util.List;
import org.springframework.http.HttpStatus;

record ApiValidationErrorResponseDto(List<String> errors, HttpStatus status) {
}

package pl.piegoose.songify.apivalidation;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.piegoose.songify.song.infrastructure.controller.SongRestController;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(assignableTypes = SongRestController.class)
public class ApiValidationErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiValidationErrorHandlerResponseDto handleValidationExcepetion(MethodArgumentNotValidException exception) {
        List<String> messages = getErrorsFromException(exception);
        return new ApiValidationErrorHandlerResponseDto(messages,HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorsFromException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}

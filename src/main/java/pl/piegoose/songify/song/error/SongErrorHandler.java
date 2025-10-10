package pl.piegoose.songify.song.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.piegoose.songify.song.controller.SongRestController;


@ControllerAdvice(assignableTypes = SongRestController.class)
@Log4j2
public class SongErrorHandler {
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorSongResponseDto handleException(SongNotFoundException e){
        log.warn("SongNotFoundException while accessing song");
        return new ErrorSongResponseDto(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}

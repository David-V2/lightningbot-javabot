package com.pictime.codinggame.basicai.game;

import com.pictime.codinggame.basicai.client.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {GameFullException.class, InvalidParameterException.class, InvalidRequestPathException.class, RequestPhaseNotInProgressException.class, RequestPhaseOverException.class, TokenAlreadyUsedException.class})
    public ResponseEntity<String> handlingServerException(final HttpServletRequest req, final HttpServletResponse resp, final Throwable t) throws IOException {
        logger.error(t.getClass().getSimpleName());
        return new ResponseEntity<>(t.getClass().toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {YouAreTheWinnerException.class, YourBotIsDeadException.class})
    public ResponseEntity<String> handlingEndGameException(final HttpServletRequest req, final HttpServletResponse resp, final Throwable t) throws IOException {
        //logger.error(t.getClass().getSimpleName());
        if(t.getClass().getSimpleName().equals("YouAreTheWinnerException")){
            logger.info("Vous avez gagné !");
        }else{
            logger.info("Vous avez perdu !");
        }
        return new ResponseEntity<>(t.getClass().toString(), HttpStatus.OK);
    }

}

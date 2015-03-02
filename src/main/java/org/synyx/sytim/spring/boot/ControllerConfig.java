/*
 * 20.09.2014
 */
package org.synyx.sytim.spring.boot;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Joachim Arrasz synyx GmbH & Co. KG
 */
@ControllerAdvice
public class ControllerConfig {

    Logger log = Logger.getLogger("ControllerConfig");

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(HttpMessageNotReadableException e) {

        log.debug(e.fillInStackTrace());
        throw e;
    }
}

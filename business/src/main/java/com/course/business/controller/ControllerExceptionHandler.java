package com.course.business.controller;

import com.course.server.dto.ResponseDto;
import com.course.server.exception.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Xiaoping Yu
 * @date 2021/5/31 - 21:13
 */

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(value = ValidatorException.class)
    @ResponseBody
    public ResponseDto validatorExceptionHandler(ValidatorException e){
        ResponseDto responseDto = new ResponseDto();
        LOG.warn(e.getMessage());
        responseDto.setSuccess(false);
        responseDto.setMessage("请求参数异常！");
        return responseDto;
    }
}

package org.LyQing.takeout.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author yjxx_2022
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    private static final String DUPLICATE_EXCEPTION = "Duplicate entry";

    /**
     * 异常处理方法
     * @param ex SQLExciton
     * @return R
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        String exMessage = ex.getMessage();
        log.error(exMessage);

        if (exMessage.contains(DUPLICATE_EXCEPTION)) {
            String[] split = exMessage.split(" ");
            String msg = split[2] + "已存在";

            return R.error(msg);
        }

        return R.error("未知错误");
    }

    /**
     * 异常处理方法
     * @param ex SQLExciton
     * @return R
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {
        String exMessage = ex.getMessage();
        log.error(exMessage);

        return R.error(ex.getMessage());
    }

}

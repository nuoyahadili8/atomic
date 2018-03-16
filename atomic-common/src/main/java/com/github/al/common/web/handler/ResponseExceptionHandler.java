package com.github.al.common.web.handler;

import com.github.al.common.Constant;
import com.github.al.common.web.entity.RetEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/10 20:51
 * @Modified By:
 */
@ControllerAdvice
public class ResponseExceptionHandler {
    @ResponseBody
    @ExceptionHandler({
            IllegalArgumentException.class,
            RuntimeException.class,
            Exception.class
    })
    public RetEntity handlerException(Exception ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String retMessage = Constant.Message.SC_INTERNAL_SERVER_ERROR + ":" + ex.getMessage();

        if(ex instanceof IllegalArgumentException) {
            status = HttpStatus.BAD_REQUEST;
            retMessage = "参数异常:" + ex.getMessage();
        } //...

        return RetEntity.error(status.value(), retMessage).setBody(getExceptionStackTraceMessage(ex));
    }


    // Loads the exception stack information into the string
    private String getExceptionStackTraceMessage(Exception ex) {
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            ex.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Cannot get the exception stack information.";
    }
}

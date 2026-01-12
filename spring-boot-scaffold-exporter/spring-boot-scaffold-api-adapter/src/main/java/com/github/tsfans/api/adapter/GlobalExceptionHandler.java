package com.github.tsfans.api.adapter;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.github.tsfans.common.enums.ResponseCode;
import com.github.tsfans.common.exception.ServiceException;
import com.github.tsfans.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler
        implements ResponseBodyAdvice<BaseResponse<Object>>, ErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> validExceptionHandler(MethodArgumentNotValidException e1,
            HttpServletRequest request) {
        log.warn("Request path [{}], BindException", request.getRequestURI(),
                e1.getMessage());
        return BaseResponse.fail(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
                e1.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ExceptionHandler(value = ServiceException.class)
    public BaseResponse<String> serviceException(HttpServletRequest request,
            ServiceException e) {
        log.warn("Rqeust path [{}], ServiceException", request.getRequestURI(),
                e);
        return BaseResponse.fail(e.getCode(), e.getMsg());
    }

    @RequestMapping(value = "/error")
    @ExceptionHandler(value = Exception.class)
    public BaseResponse<String> error(HttpServletRequest request, Exception e) {
        log.warn("Request path [{}], Exception:", request.getRequestURI(),
                e);
        HttpStatus status = getStatus(request);
        String msg = e.getMessage() == null ? status.getReasonPhrase() : e.getMessage();
        return BaseResponse.fail(status.value() + "", msg);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("jakarta.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    @Override
    public boolean supports(MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getReturnType()
                .isAssignableFrom(BaseResponse.class);
    }

    @Override
    public BaseResponse<Object> beforeBodyWrite(BaseResponse<Object> body,
            MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.valueOf(Integer.valueOf(body.getCode())));
        return body;
    }

}

package cn.swift.controllers.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
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

import cn.swift.common.enums.ResponseCode;
import cn.swift.common.exception.ServiceException;
import cn.swift.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ControllerAdvice
public class ExceptionAndHttpstatusHandler
    implements ResponseBodyAdvice<BaseResponse<Object>>, ErrorController {

  private static final String PATH = "/error";

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public BaseResponse<String> validExceptionHandler(MethodArgumentNotValidException e,
                                                    HttpServletRequest request) {
    log.warn("Request path [{}], BindException: {}", request.getRequestURI(), e.getMessage());
    return BaseResponse.fail(ResponseCode.ILLEGAL_ARGUMENT.getCode(),
        e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }

  @ExceptionHandler(value = ServiceException.class)
  public BaseResponse<String> serviceException(HttpServletRequest request, ServiceException e) {
    log.warn("Rqeust path [{}], ServiceException: {}", request.getRequestURI(), e.getMsg(), e);
    return BaseResponse.fail(e.getCode(), e.getMsg());
  }

  @RequestMapping(value = PATH)
  @ExceptionHandler(value = Exception.class)
  public BaseResponse<String> error(HttpServletRequest request, Exception e) {
    long errorCode = System.currentTimeMillis();
    HttpStatus status = getStatus(request);
    String msg = e.getMessage() == null ? status.getReasonPhrase() : e.getMessage();
    return BaseResponse.fail(status.value() + "", msg);
  }

  @Override
  public BaseResponse<Object> beforeBodyWrite(BaseResponse<Object> br,
                                              MethodParameter var2,
                                              MediaType var3,
                                              Class<? extends HttpMessageConverter<?>> var4,
                                              ServerHttpRequest req,
                                              ServerHttpResponse res) {
    if (br.getCode().equals("400")) {
      res.setStatusCode(HttpStatus.BAD_REQUEST);
    }
    if (br.getCode().equals("500")) {
      res.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return br;
  }

  @Override
  public boolean supports(MethodParameter returnType,
                          Class<? extends HttpMessageConverter<?>> var2) {
    return returnType.getMethod().getReturnType().isAssignableFrom(BaseResponse.class);
  }

  @Override
  public String getErrorPath() {
    return PATH;
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    return HttpStatus.valueOf(statusCode);
  }

}

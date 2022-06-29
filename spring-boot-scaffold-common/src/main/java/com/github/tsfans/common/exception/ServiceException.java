package com.github.tsfans.common.exception;

import com.github.tsfans.common.enums.ResponseCode;

public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private final String code;
  private final String msg;

  public ServiceException(String code, String msg) {
    super(code + ":" + msg);
    this.code = code;
    this.msg = msg;
  }

  public ServiceException(String code, String msg, Throwable cause) {
    super(code + ":" + msg, cause);
    this.code = code;
    this.msg = msg;
  }

  public ServiceException(ResponseCode responseCode) {
    super(responseCode.getCode() + ":" + responseCode.getMsg());
    this.code = responseCode.getCode();
    this.msg = responseCode.getMsg();
  }

  public ServiceException(ResponseCode responseCode, Throwable cause) {
    super(responseCode.getCode() + ":" + responseCode.getMsg(), cause);
    this.code = responseCode.getCode();
    this.msg = responseCode.getMsg();
  }

  public String getMsg() {
    return this.msg;
  }

  public String getCode() {
    return this.code;
  }

}

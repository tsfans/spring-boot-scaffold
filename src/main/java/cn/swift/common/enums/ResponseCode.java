package cn.swift.common.enums;

public enum ResponseCode {

  SUCCESS("1", "成功"), 
  ILLEGAL_ARGUMENT("0", "参数错误"),
  FAILURE("-1", "失败");
  
  private final String code;
  private final String msg;

  public String getCode() {
    return this.code;
  }

  public String getMsg() {
    return this.msg;
  }
  
  private ResponseCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }
  
  @Override
  public String toString() {
    return "ResponseCode(code=" + this.getCode() + ", msg=" + this.getMsg() + ")";
  }

  
}

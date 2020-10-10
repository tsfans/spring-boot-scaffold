package cn.swift.common.response;

import cn.swift.common.enums.ResponseCode;
import lombok.Data;

@Data
public class BaseResponse<T> {

  private String code = ResponseCode.SUCCESS.getCode();
  private String msg = ResponseCode.SUCCESS.getMsg();
  private T data;

  public static<T> BaseResponse<T> success(T data) {
      BaseResponse<T> resp = new BaseResponse<>();
      resp.setCode(ResponseCode.SUCCESS.getCode());
      resp.setMsg(ResponseCode.SUCCESS.getMsg());
      resp.setData(data);
      return resp;
  }

  public static<T> BaseResponse<T> fail(ResponseCode responseCode) {
      BaseResponse<T> resp = new BaseResponse<>();
      resp.setCode(responseCode.getCode());
      resp.setMsg(responseCode.getMsg());
      return resp;
  }

  public static<T> BaseResponse<T> fail(String code, String msg) {
      BaseResponse<T> resp = new BaseResponse<>();
      resp.setCode(code);
      resp.setMsg(msg);
      return resp;
  }


}

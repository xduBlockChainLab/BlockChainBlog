package com.bc208.blog.utils;

/**
 * @author QingheLi
 */
public class ParamsException extends RuntimeException{
    public String Msg;
    public Integer Code= 200;

    public ParamsException(String msg) {
      this.Msg=msg;
    }
    public Integer getCode() {

         return Code;
    }
    public String getMsg(){
        return Msg;
    }
}

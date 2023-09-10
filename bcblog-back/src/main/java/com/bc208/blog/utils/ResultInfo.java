package com.bc208.blog.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author QingheLi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultInfo {
    private  Integer code;
    private  String msg;
    private  Object result = "null";


    public ResultInfo success(){
        return new ResultInfo(2000, "success", null);
    }

    public ResultInfo success(int code, String msg){
        return new ResultInfo(code, msg, null);
    }

    public ResultInfo success(int code, String msg, Object result){
        return new ResultInfo(code, msg, result);
    }

    public ResultInfo error(String msg){
        return new ResultInfo(5000, msg, null);
    }

    public ResultInfo error(Integer code, String msg){
        return new ResultInfo(code, msg, null);
    }

    /**
     * 模糊回复, 避免外人查看情况. 不能直接告诉外界出现什么错误
     */
    public ResultInfo error(){
        return new ResultInfo(5000, "system error", null);
    }

}

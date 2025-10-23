package com.linfeng.spring1910.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Result<E>{
    public Integer code;
    public String message;
    public E data;

    public static <E> Result<E> success(E info){
        return new Result<>(0,"success",info);
    }
    public static Result success(){
        return new Result(1,"success",null);
    }
    public static Result error(String message){
        return new Result(-1,message,null);
    }
    public String toString(){
        return "{\"code\":"+code+",\"message\":\""+message+"\",\"data\":"+data+"}";
    }
}

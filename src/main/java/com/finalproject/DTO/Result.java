package com.finalproject.DTO;

public class Result<T> {
    //状态码
    private int code;
    //消息
    private String msg;
    //数据：我们不确定数据的类型，所以用泛型
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // 请求成功 不返回数据
    public static <T> Result<T> success() {
        Result rs = new Result<>();
        rs.setCode(200);
        rs.setMsg("ok");
        return rs;
    }

    // 请求成功 返回数据
    public static <T> Result<T> success(T data) {
        Result<T> rs = new Result<T>(data);
        rs.setCode(200);
        rs.setMsg("ok");
        return rs;
    }

    // 不返回数据，且想想自定义成功信息
    public static <T> Result<T> success(int code, String msg) {
        Result rs = new Result<>();
        rs.setCode(code);
        rs.setMsg(msg);
        return rs;
    }

    // 请求失败 -- 因为失败的原因不确定，所以不能指定
    public static <T> Result<T> error(int code, String msg) {
        //失败了就不需要返回数据给用户
        Result rs = new Result<>();
        rs.setCode(code);
        rs.setMsg(msg);
        return rs;
    }
}
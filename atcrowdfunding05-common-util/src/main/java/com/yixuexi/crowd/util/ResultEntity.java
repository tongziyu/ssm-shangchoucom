package com.yixuexi.crowd.util;

import java.util.Objects;

/**
 * @date: 2021/1/11   15:09
 * @author: 易学习
 * 统一整个项目中，AJAX请求返回的结果
 * 未来也可以用来分布式架构各个模块间调用时返回的数据
 * <T> 泛型，将来查询数据，查询到什么数据就是什么类型
 */
public class ResultEntity<T> {
    /**
     *    为了规范处理成功后返回什么，定义两个常量
     */
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    /**
     * 用来封装当前请求处理的结果是成功还是失败
     */
    private String result;

    /**
     * 请求处理失败时，返回的错误消息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;


    /**
     * 处理成功但是不需要返回数据
     * @param <E>前面的<E> 是声明一个泛型方法，后面的E是使用泛型
     * @return
     */
    public static <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(SUCCESS,null,null);
    }

    /**
     * 处理成功 并且需要返回数据
     * @param <E>
     * @return
     */
    public static <E> ResultEntity<E> successWithData(E data){
        return new ResultEntity<>(SUCCESS,null,data);
    }

    /**
     * 处理失败，需要返回一个提示消息
     * @param message  出错的原因
     * @return
     */
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<E>(FAILED,message,null);
    }
    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if
        (this == o) {return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        ResultEntity<?> that = (ResultEntity<?>) o;
        return Objects.equals(result, that.result) &&
                Objects.equals(message, that.message) &&
                Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, message, data);
    }
}

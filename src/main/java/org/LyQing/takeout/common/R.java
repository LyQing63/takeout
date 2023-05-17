package org.LyQing.takeout.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yjxx_2022
 * @param <T>
 * 通用返回结果,服务器响应的数据最终都会封装成此对象
 */
@Data
public class R<T> {

    /**
     * 编码：1成功，0和其他为失败
     * */
    private Integer code;

    /**
     * 错误信息
     * */
    private String msg;

    /**
     * 数据
     * */
    private T data;

    /**
     * 动态数据
     * */
    private Map map = new HashMap();

    /**
     * 请求成功
     * */
    public static <T> R<T> success(T object) {

        R<T> r= new R<>();
        r.data = object;
        r.code = 1;
        return r;

    }


    /**
     * 请求失败
     * */
    public static <T> R<T> error(String msg) {

        R<T> r= new R<>();
        r.msg = msg;
        r.code = 0;
        return r;

    }

    /**
     * 请求失败
     * */
    public R<T> add(String key, Object value) {

        this.map.put(key, value);
        return this;

    }

}

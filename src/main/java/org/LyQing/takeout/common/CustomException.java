package org.LyQing.takeout.common;

/**
 * 自定义业务异常
 *
 * @author yjxx_2022
 * @date 2023/05/22
 */
public class CustomException extends RuntimeException{

    public CustomException(String msg) {
        super(msg);
    }

}

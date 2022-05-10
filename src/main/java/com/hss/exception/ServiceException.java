package com.hss.exception;

/**
 * 自定义服务异常
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super();
    }


    public ServiceException(String message) {
        super(message);
    }
}

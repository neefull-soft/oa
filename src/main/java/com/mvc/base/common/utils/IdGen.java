package com.mvc.base.common.utils;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

public class IdGen implements IdGenerator {

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public String getNextId() {
        String uuid = uuid() ;
        return uuid ;
    }
}

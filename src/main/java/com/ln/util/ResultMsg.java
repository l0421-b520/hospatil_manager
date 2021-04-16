package com.ln.util;

/**
 * @作者: 李跃辉
 * @时间: 2021/4/8 18:46
 */

import lombok.Data;

/**
 *  返回结果集工具类
 */

public class ResultMsg {
    private  Integer status;

    private  String msg;

    public ResultMsg(){}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultMsg(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }


}

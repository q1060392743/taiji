package cn.com.taiji.taiji.utils;

import java.io.Serializable;

/**
 * 执行结果
 *
 * @author SongChong created by10:31 2018/11/29 0029
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    //结果代码
    private int code;

    //结果信息
    private String msg;

    //结果携带的数据
    private Object data;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

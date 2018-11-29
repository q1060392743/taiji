package cn.com.taiji.taiji.utils;

public class ResultUtils {

    //成功
    public static Result Success() {
        return new Result(1, "操作成功");
    }

    //成功时自定义信息
    public static Result Success(String msg) {
        return new Result(1, msg);
    }

    //失败
    public static Result Error() {
        return new Result(-1, "操作失败");
    }
}

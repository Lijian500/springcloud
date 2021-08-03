package lj.springcloud.sample.common.constant;

public class ResultCodeConstant {

    public final static Integer SUCCESS = 1000; // 成功

    public final static Integer FAIL = 1001; // 操作失败

    public final static Integer PARAMETER_FAIL = 1002; // 参数缺失/有误：%s

    public final static Integer NOT_LOGIN = 1003;//没有登录，请先登录

    public final static Integer NO_ACCESS = 1004;//没有权限访问该资源

    public final static Integer GET_ACCESS_FAIL = 1005;//获取用户权限失败

    public final static Integer FAIL_REASON = 1006; // 操作失败：%s

    public final static Integer FAIL_MSG = 1007; // %s

    public final static Integer CAN_NOT_LOGIN = 1008; // 没有登录权限


}
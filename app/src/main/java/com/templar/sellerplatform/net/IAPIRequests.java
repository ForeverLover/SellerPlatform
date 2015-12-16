package com.templar.sellerplatform.net;

/**
 * Created by jph on 2015/8/12.
 */
public interface IAPIRequests {

    /**
     * 登录
     *
     * @param phone 手机号
     * @param pswd  密码
     */
    void login(String phone, String pswd);


}

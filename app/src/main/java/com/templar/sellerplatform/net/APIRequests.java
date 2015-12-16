package com.templar.sellerplatform.net;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by jph on 2015/8/12.
 */
public class APIRequests extends BaseAPIRequest implements IAPIRequests {

    private XRequestCallBack XRequestCallBack;

    public APIRequests(XRequestCallBack XRequestCallBack) {
        this.XRequestCallBack = XRequestCallBack;
    }

    @Override
    public void login(String phone, String pswd) {
        RequestParams requestParams = createRequestParams();
        requestParams.addBodyParameter("iphone", phone);
        requestParams.addBodyParameter("pwd", pswd);
        request(XRequestCallBack, Tasks.LOGIN, "/login", requestParams, null);
    }


}

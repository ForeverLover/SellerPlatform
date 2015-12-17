package com.templar.sellerplatform.parser;

import com.templar.sellerplatform.entity.Administrator;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 19:20
 * 描述：${TODO}
 */
public class UserParser {
    private static UserParser instance = null;

    public static UserParser getInstance() {
        if (instance == null)
            instance = new UserParser();
        return instance;
    }

    public Administrator parseAdministrator(/*String data*/) {
       /* if (StringUtils.isEmpty(data))
            return null;*/
        Administrator administrator = null;
        administrator=new Administrator();
        administrator.setMerchant_id("114-283");
        administrator.setPhone("0976232111");
        administrator.setAccount("sojajo 102");
        administrator.setPwd("Sa14b283kk");
        administrator.setSuper_pwd("123123");
        administrator.setAvatar("http://pic14.nipic.com/20110522/7411759_164157418126_2.jpg");

        return administrator;

    }
}

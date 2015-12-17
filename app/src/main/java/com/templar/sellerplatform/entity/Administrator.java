package com.templar.sellerplatform.entity;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 17:48
 * 描述：${TODO}
 */
public class Administrator {
    private String avatar;
    private String merchant_id;
    private String account;
    private String super_pwd;
    private String phone;
    private String pwd;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSuper_pwd() {
        return super_pwd;
    }

    public void setSuper_pwd(String super_pwd) {
        this.super_pwd = super_pwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}

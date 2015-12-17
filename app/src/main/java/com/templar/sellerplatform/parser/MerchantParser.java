package com.templar.sellerplatform.parser;

import com.templar.sellerplatform.entity.MerchantInformation;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/17 14:39
 * 描述：${TODO}
 */
public class MerchantParser {
    private static MerchantParser instance = null;

    public static MerchantParser getInstance() {
        if (instance == null)
            instance = new MerchantParser();
        return instance;
    }

    public MerchantInformation parseMerchantInfo(/*String data*/) {
//        if (StringUtils.isEmpty(data))
//            return null;
        MerchantInformation information = null;
        /*try {
            JSONObject mObject=new JSONObject(data);
            if (mObject!=null){
                information=new MerchantInformation();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        information = new MerchantInformation();
        information.setMerchant_id("114-283");
        information.setMerchant_bg("http://image1.huangye88.com/2012/09/10/15c368d4f374110b.jpg");
        information.setMerchant_name("五十嵐");
        information.setMerchant_name_vice("敦化店");
        information.setMerchant_addr("台北市大安區忠孝東路27號-3");
        information.setMerchant_card("法式卡布奇諾 熱賣中~ 滿百即可外送");
        information.setMerchant_remark("歡迎攜帶寵物");
        information.setMerchant_link("02-8825252");
        information.setMerchant_showTime("am09~pm10 每週一公休");
        information.setMerchant_remark("歡迎攜帶寵物");information.setMerchant_web("http://www.baidu.com");
        return information;
    }
}

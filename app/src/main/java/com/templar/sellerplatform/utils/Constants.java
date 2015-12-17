package com.templar.sellerplatform.utils;

import android.os.Environment;

import java.io.File;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/16 18:10
 * 描述：$TODO
 */
public class Constants {

    public interface Key {
        String ONE_APM = "4EAD362BFE47607A3D4706BB708F2F2249";
    }

    public interface Path {
        String PATH_BASE = Environment.getExternalStorageDirectory() + File.separator + "Seller";

        String PATH_VOICE = PATH_BASE + File.separator + "Voice";

        String PATH_PIC = PATH_BASE + File.separator + "Photo";

        String PATH_CACHE = PATH_PIC + File.separator + "Cache";

        String UPDATEA_PATH = PATH_BASE + File.separator + "seller'splatfrom.apk";

        String PATH_CRASH = PATH_BASE + File.separator + "CrashLog";
    }

    public interface Url {
        String BASE_URL = "http://captainoak.cn";
        String URL_PREFIX_FILE = Url.BASE_URL + "/";
    }

    public interface Intent{
        interface Variable{
            String MERCHANT_INFO="merchant_info";
            String ADMINISTRATOR_INFO="administrator_info";
            String ADMINISTRATOR_SUPER_PWD="administrator's_super_pwd";
        }
        interface Action{

        }
    }

    public interface Code{
        /**
         * 修改商铺信息
         */
        static final int MODIFY_MERCHANT =300;
        static final int MODIFY_SUPER_CODE=301;
    }
}

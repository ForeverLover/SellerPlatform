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

    public interface Intent {
        interface Variable {
            String MERCHANT_INFO = "merchant_info";
            String ADMINISTRATOR_INFO = "administrator_info";
            String ADMINISTRATOR_SUPER_PWD = "administrator's_super_pwd";
            String MENU_SINGLE = "menu_single";
            String MENU_SINGLE_ID = "menu_single_id";
            String MENU_VICE = "menu_vice";
            String MENU_VICE_ID = "menu_vice_id";
        }

        interface Action {

        }
    }

    public interface Code {
        /**
         * 修改商铺信息
         */
        int MODIFY_MERCHANT = 300;
        int MODIFY_SUPER_CODE = 301;
        /**
         * 新增菜单单品
         */
        int ADD_MENU_SINGLE = 302;
        /**
         * 编辑菜单单品
         */
        int MODIFY_MENU_SINGLE = 303;
        /**
         * 菜單新增副選項
         */
        int ADD_MENU_VICE = 304;
        /**
         * 编辑副選項
         */
        int MODIFY_MENU_VICE = 305;
        /**
         * 新增副选项
         */
        int ADD_VICE = 306;
    }
}

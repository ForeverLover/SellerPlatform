package com.templar.sellerplatform.utils;

import com.templar.sellerplatform.R;
import com.templar.sellerplatform.ui.fragment.DynamicFragment;
import com.templar.sellerplatform.ui.fragment.ModifyFramgnent;
import com.templar.sellerplatform.ui.fragment.SettingsFragment;
import com.templar.sellerplatform.ui.fragment.TogglViceFragment;
import com.templar.sellerplatform.ui.fragment.ToggleFragment;
import com.templar.sellerplatform.ui.fragment.ToggleMenuFragment;

/**
 * 项目:SellerPlatform
 * 作者：Hi-Templar
 * 创建时间：2015/12/18 11:25
 * 描述：初始化tab信息
 */
public class TabUtils {
    public interface MainTab {
        public static int[] tabsTextId = {R.string.tab_dynamic, R.string.tab_toogle, R.string.tab_edit, R.string.tab_settings};

        public static int[] tabsCheckedImg = {R.mipmap.dynamic_img_checked, R.mipmap.toggle_img_checked, R.mipmap.edit_img_checked, R.mipmap.settings_img_checked};

        public static int[] tabUncheckImg = {R.mipmap.dynamic_img_uncheck, R.mipmap.toogle_img_uncheck, R.mipmap.edit_img_uncheck, R.mipmap.settings_img_uncheck};

        public static Class[] TabFragment = {DynamicFragment.class, ToggleFragment.class, ModifyFramgnent.class, SettingsFragment.class};
    }

    public interface ProTab {
        public static int[] tabsTextId = {R.string.tab_menu, R.string.tab_vice};
        public static Class[] TabToggleFragment = {ToggleMenuFragment.class, TogglViceFragment.class};
//        public static Class[] TabToggleFragment = {};
    }

    public interface Dynamic{
        public static int[] tabsTextId = {R.string.dynamic_notice, R.string.dynamic_deal, R.string.dynamic_wait, R.string.dynamic_end};
    }

}

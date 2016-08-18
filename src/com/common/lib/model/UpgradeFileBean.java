
package com.common.lib.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UpgradeFileBean implements Serializable {
    public static final String UPGRADE_FILE_BEAN = "upgradeFileBean";
    /**
     * 当前检查接口
     */
    public String checkUrl;
    /**
     * 系统类型，1：android,2:ios
     */
    public String os;

    /**
     * 城市
     */
    public String city = "";
    /**
     * version
     */
    public int version;

    /**
     * 更新说明
     */
    public String explains;

    /**
     * 更新地址
     */
    public String update_url;

    /**
     * 更新日期(2015-07-07)
     */
    public String pub_date;

    /**
     * 是否强制更新，0：否，1：是
     */
    public int forced_update;
    /**
     * 0：物流 1:商户2：用户
     */
    public int os_type = 0;

}

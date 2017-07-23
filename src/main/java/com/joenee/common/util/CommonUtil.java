package com.joenee.common.util;

import org.apache.commons.lang.StringUtils;

/**
 * CommonUtil
 *
 * @author Li zheng
 * @description
 * @date 2016/4/22
 */
public class CommonUtil {

    /**
     * 去除字符串最后一个逗号,若传入为空则返回空字符串
     *
     * @descript
     * @param para
     */
    public static String trimComma(String para) {
        if (StringUtils.isNotBlank(para)) {
            if (para.endsWith(",")) {
                return para.substring(0, para.length() - 1);
            } else {
                return para;
            }
        } else {
            return "";
        }
    }
}

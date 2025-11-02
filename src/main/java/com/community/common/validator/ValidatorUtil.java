package com.community.common.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class ValidatorUtil {
    
    /**
     * 手机号验证
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isBlank(mobile)) {
            return false;
        }
        String regex = "^1[3-9]\\d{9}$";
        return Pattern.matches(regex, mobile);
    }
    
    /**
     * 身份证号验证
     */
    public static boolean isIdCard(String idCard) {
        if (StringUtils.isBlank(idCard)) {
            return false;
        }
        String regex = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        return Pattern.matches(regex, idCard);
    }
    
    /**
     * 邮箱验证
     */
    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);
    }
    
    /**
     * 车牌号验证
     */
    public static boolean isLicensePlate(String licensePlate) {
        if (StringUtils.isBlank(licensePlate)) {
            return false;
        }
        String regex = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";
        return Pattern.matches(regex, licensePlate);
    }
}
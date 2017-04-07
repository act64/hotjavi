package com.hotjavi.Util;

import com.mysql.jdbc.StringUtils;

/**
 * Created by ylei on 17-4-5.
 */
public class CheckInvailedUtil {
    private static final String regexAccouynt="^[A-Za-z]\\w{4,15}$";
    private static final String regexpwd="^[A-Za-z]\\w{5,15}$";
    private static final String EmailRegex="^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final String nicknameRegex = "^[\\u4e00-\\u9fa5_a-zA-Z0-9-]{1,16}$";
    public static boolean checkAccount(String accountstr){
        return checkStr(accountstr,regexAccouynt);
    }

    public static boolean checkpwd(String pwdstr){
     return    checkStr(pwdstr,regexpwd);
    }

    public static boolean checkEmail(String emailStr){
        return checkStr(emailStr,EmailRegex);
    }

    public static boolean checkNickName(String nickNamestr){
        return checkStr(nickNamestr,nicknameRegex);
    }


    private static boolean checkStr(String inStr,String regex){
        if (StringUtils.isNullOrEmpty(inStr)){
            return false;
        }
        return inStr.matches(regex);
    }
}

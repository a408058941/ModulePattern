package com.fd.baselibrary.utils;

public class SPManager extends BaseSPManager {
    public static final String USER_ISLOGIN = "user_islogin"; //是否登录
    public static final String USER_CODE = "userCode"; //用户编码
    public static final String USER_NICK = "userNick"; //用户昵称
    public static final String USER_TELE = "userTele"; //用户手机
    public static final String USER_HEAD= "userHead"; //用户头像
    public static final String ATTE_CODE= "atte_code"; //用户属性编码'
    public static final String LABL_CODE= "labl_code"; //用户标签编码,分隔','
    public static final String USER_GENR= "user_genr"; //性别
    public static final String USER_COREID= "user_coreId"; //核心用户编码



    public static String getLogin_cookie() {
        return (String) SPUtil.get(USER_ISLOGIN,"");
    }

    public static void setLogin_cookie(String login_cookie) {
        SPUtil.put(USER_ISLOGIN, login_cookie);
    }



    public static String getUserCode() {
        return (String) SPUtil.get(USER_CODE,"");
    }

    public static void setUserCode(String userCode) {
        SPUtil.put(USER_CODE, userCode);
    }

    public static String getUserNick() {
        return (String) SPUtil.get(USER_NICK,"");
    }

    public static void setUserNick(String userNick) {
        SPUtil.put(USER_NICK, userNick);
    }


    public static String getUserTele() {
        return (String) SPUtil.get(USER_TELE,"");
    }

    public static void setUserTele(String userTele) {
        SPUtil.put(USER_TELE, userTele);
    }


    public static String getUserHead() {
        return (String) SPUtil.get(USER_HEAD,"");
    }

    public static void setUserHead(String userHead) {
        SPUtil.put(USER_HEAD, userHead);
    }



    public static String getAtteCode() {
        return (String) SPUtil.get(ATTE_CODE,"");
    }

    public static void setAtteCode(String atte_code) {
        SPUtil.put(ATTE_CODE, atte_code);
    }


    public static String getLablCode() {
        return (String) SPUtil.get(LABL_CODE,"");
    }

    public static void setLablCode(String labl_code) {
        SPUtil.put(LABL_CODE, labl_code);
    }


    public static String getUserGenr() {
        return (String) SPUtil.get(USER_GENR,"");
    }

    public static void setUserGenr(String labl_code) {
        SPUtil.put(USER_GENR, labl_code);
    }
    public static String getUser_CoreIdr() {
        return (String) SPUtil.get(USER_COREID,"");
    }

    public static void setUser_CoreIdr(String labl_code) {
        SPUtil.put(USER_COREID, labl_code);
    }



}

package com.community.domain.session;


import org.apache.commons.lang.StringUtils;

/**
 * 本地线程会话
 */
public class LoginContext {

    private static final ThreadLocal<UserSession> local = new ThreadLocal<>();

    /**
     * 设置当前会话用户信息
     *
     * @param userDetail 用户信息
     */
    public static void setUserDetail(UserDetail userDetail) {
        local.set((UserSession) userDetail);
    }

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    public static UserDetail getUserDetail() {
        return local.get();
    }

    public static long getUserId() {
        UserDetail user = local.get();
        return user == null ? -1 : user.getUserId();
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        UserDetail user = local.get();
        return user == null ? null : user.getUserName();
    }

    public static boolean isLogin() {
        return StringUtils.isNotEmpty(getUserName());
    }

}

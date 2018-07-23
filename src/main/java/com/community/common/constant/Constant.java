package com.community.common.constant;

import com.community.domain.model.db.UserInfoDO;

public class Constant {

    public static final String USER_ID_KEY = "userId";

    /**
     * 设备
     */
    public static final String DEVICE_TYPE = "device_type";
    public static final Integer DEVICE_TYPE_WEB = 0;
    public static final Integer DEVICE_TYPE_PC = 1;
    public static final Integer DEVICE_TYPE_APP = 2;

    /**
     * 默认数
     */
    public static final int DEFAULT_NUM = 6;
    /**
     * 置顶默认数
     */
    public static final int TOP_DEFAULT_NUM = 6;
    /**
     * Future默认超时数
     */
    public static final int FUTURE_TIMEOUT = 2000;
    /**
     * 摘要最大数
     */
    public static final int MAX_SUMMARY_LENGTH = 160;
    /**
     * 公共活动
     */
    public static final Integer PUBLIC_ACTIVITY_ORG_ID = -1;
    /**
     * 群组最大人数
     */
    public static final int MAX_GROUP_MEMBER_NUM = 500;

    public enum IsAnonymousEnum {
        YES(-1, "匿名"),
        NO(1, "不匿名"),;

        private Integer code;
        private String desc;

        IsAnonymousEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public enum MessageTypeEnum {
        SYSTEM(1, "系统"),
        USER(2, "用户"),;
        private Integer code;
        private String desc;

        MessageTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum IsReadEnum {
        UN_READ(-1, "未读"),
        READ(1, "已读"),;

        private Integer code;
        private String desc;

        IsReadEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum WsChannelTypeEnum {
        FRIEND(1, "朋友"),
        GROUP(2, "群主"),;
        private Integer code;
        private String desc;

        WsChannelTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum FollowStatusEnum {
        BLACK_LIST(-2, "黑名单"),
        CANCEL(-1, "取消"),
        FOLLOW(1, "关注"),;

        private Integer code;
        private String desc;

        FollowStatusEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum IsDeleteEnum {
        NO(0, "未删除"),
        YES(1, "已删除");
        private Integer code;
        private String desc;

        IsDeleteEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static UserInfoDO DEFAULT_USER_INFO = new UserInfoDO();

    static {
        DEFAULT_USER_INFO.setAvatar("ddddd");
    }

    public enum ShowEnum {
        SHOW(0, "展示"),
        HIDE(-1, "隐藏"),;
        private Integer code;
        private String desc;

        ShowEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum LikeEnum {
        LIKE(1, "点赞"),
        CANCEL(-1, "取消点赞"),;
        private Integer code;
        private String desc;

        LikeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    //1-系统消息、2-点赞、2-评论、3-问答、4-活动
    public enum TypeEnum {
        SYSTEM(100, "系统"),
        USER(110, "用户"),
        ORG(200, "组织"),
        ACTIVITY(300, "活动"),
        ASK(300, "问题"),
        ANSWER(400, "回答"),
        COMMENT(500, "评论"),
        REPLY(600, "回复"),
        UP(700, "点赞"),;

        TypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static boolean isContentType(Integer type) {
            if (ORG.getCode().equals(type) || ACTIVITY.getCode().equals(type) || ASK.getCode().equals(type) || ANSWER.getCode().equals(type)) {
                return true;
            }
            return false;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum ContextTypeEnum {
        IMG(1, "图片"),
        TEXT(2, "文本"),;

        private Integer code;
        private String desc;

        ContextTypeEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum RoleEnum {
        UN_KNOWN(-2, " 未知"),
        UN_LOGIN(-1, "未登录"),
        ADMIN(100, "管理员"),
        COMMITTEE(200, "班委"),
        ORDINARY(300, "普通用户"),;

        private Integer code;
        private String desc;

        RoleEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    public enum SortFieldEnum {
        ID("id", "主键"),
        CREATE_TIME("create_time", "创建时间"),
        UPDATE_TIME("update_time", "修改时间"),;
        private String code;
        private String desc;

        SortFieldEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public enum SortOrderEnum {
        DESC("desc", "降序"),
        ASC("asc", "升序"),;

        private String code;
        private String desc;

        SortOrderEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}

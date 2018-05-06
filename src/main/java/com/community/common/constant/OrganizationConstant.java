package com.community.common.constant;

public class OrganizationConstant {


    public enum StatusEnum {
        REJECT(-2, "拒绝加入"),
        CANCEL(-1, "取消关注"),
        FOLLOW(1, "关注"),;
        private Integer code;
        private String desc;

        StatusEnum(Integer code, String desc) {
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
}

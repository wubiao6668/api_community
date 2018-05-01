package com.community.common.constant;

public class ContentConstant {

    public enum TopEnum {
        TOP_NO(0, "不置顶"),
        TOP_YES(1, "置顶"),;
        private int code;
        private String desc;

        TopEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
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

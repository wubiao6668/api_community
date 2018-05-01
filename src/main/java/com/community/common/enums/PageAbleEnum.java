package com.community.common.enums;

public enum PageAbleEnum {

    IS_MORE(1, "是否有更多"),
    TOTAL(2, "总的"),
    LIMIT(3, "一定页数的"),;


    private Integer code;
    private String desc;

    PageAbleEnum(Integer code, String desc) {
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

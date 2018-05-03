package com.community.domain.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContentBO implements Serializable {
    private static final long serialVersionUID = -2247730560290931955L;

    private Integer type;
    private String text;

}

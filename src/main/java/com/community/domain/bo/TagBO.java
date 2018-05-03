package com.community.domain.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagBO implements Serializable {
    private static final long serialVersionUID = -2636735377630292792L;

    private Integer type;
    private String name;

}

package com.community.domain.bo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AtInfoBO implements Serializable {

    private static final long serialVersionUID = 893214513373650441L;

    private Long userId;

    public AtInfoBO(Long userId) {
        this.userId = userId;
    }
}

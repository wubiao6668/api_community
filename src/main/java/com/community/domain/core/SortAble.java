package com.community.domain.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class SortAble implements Serializable {
    private String fieldName;
    private String sortOrder;

    public SortAble(String fieldName, String sortOrder) {
        this.fieldName = fieldName;
        this.sortOrder = sortOrder;
    }
}

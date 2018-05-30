package com.community.domain.model.db.extend;

import com.community.domain.model.db.UserMessageDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserMessageExtendDO extends UserMessageDO implements Serializable {
    private static final long serialVersionUID = 8292214851557608188L;

    private Integer shortUnReadMsgNumInc;

    private Integer longUnReadMsgNumInc;


    @Override
    public String toString() {
        return super.toString();
    }
}

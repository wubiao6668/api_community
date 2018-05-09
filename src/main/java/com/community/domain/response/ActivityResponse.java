/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.domain.model.db.ActivityDO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityResponse extends ActivityDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    @Override
    public String toString() {
        return super.toString();
    }
}


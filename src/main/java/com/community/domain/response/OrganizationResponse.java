/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.domain.model.db.OrganizationDO;
import com.community.domain.response.vo.OrganizationVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrganizationResponse extends OrganizationDO implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private OrganizationVO organization;

    @Override
    public String toString() {
        return super.toString();
    }
}


/**
 * Created by wubiao on - 2018/04/29.
 */

package com.community.domain.response;

import com.community.domain.core.Page;
import com.community.domain.response.vo.ReplyVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ReplyResponse implements Serializable {
    private static final long serialVersionUID = 5454155825314635342L;

    private Page<ReplyVO> page;

    @Override
    public String toString() {
        return super.toString();
    }
}

